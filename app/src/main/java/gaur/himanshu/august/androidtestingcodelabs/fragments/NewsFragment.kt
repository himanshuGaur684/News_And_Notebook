package gaur.himanshu.august.androidtestingcodelabs.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.Status
import gaur.himanshu.august.androidtestingcodelabs.adapters.NewsAdapter
import gaur.himanshu.august.androidtestingcodelabs.adapters.NewsClickListioners
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Source
import gaur.himanshu.august.androidtestingcodelabs.viewmodels.NewsViewModels
import gaur.himanshu.august.androidtestingcodelabs.viewmodels.NoteViewModels
import kotlinx.android.synthetic.main.fragment_news.view.*

@AndroidEntryPoint
class NewsFragment : Fragment() , NewsClickListioners{



    private val viewModel by viewModels<NewsViewModels>()
    private val newsAdapter = NewsAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        

        viewModel.newsList.observe(viewLifecycleOwner) {
            when (it.peekContent().status) {
                Status.LOADING -> {
                    showProgress(view)
                }
                Status.ERROR -> {
                    hideProgress(view)
                    Snackbar.make(requireView(),"Error Occurred",Snackbar.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    hideProgress(view)
                    it.peekContent().data?.let { it1 -> newsAdapter.setContentList(it1) }
                    view.news_recycler?.also {
                        it.adapter = newsAdapter
                    }
                }
            }
        }
    }


    private fun showProgress(view: View) {
        view.news_progress.visibility = View.VISIBLE
    }

    private fun hideProgress(view: View) {
        view.news_progress.visibility = View.GONE
    }

    override fun newsHandler(article: Article, position: Int) {
        findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(article))
    }

}