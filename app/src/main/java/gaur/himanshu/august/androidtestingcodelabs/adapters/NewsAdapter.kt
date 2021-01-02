package gaur.himanshu.august.androidtestingcodelabs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gaur.himanshu.august.androidtestingcodelabs.BR
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.databinding.ListItemNewsBinding
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article
import kotlinx.android.synthetic.main.list_item_news.view.*

class NewsAdapter(private val newsClickListioners: NewsClickListioners) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {


    private var list = listOf<Article>()

    fun setContentList(list: List<Article>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val binding =
            ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.article, list[position])

        Glide.with(holder.viewDataBinding.root).load(list[position].urlToImage)
            .placeholder(R.drawable.ic_baseline_event_note_24)
            .into(holder.viewDataBinding.root.news_image)

        holder.viewDataBinding.root.news_detail_root.setOnClickListener {

        newsClickListioners.newsHandler(list[position],position)

        // it.findNavController().navigate(R.id.action_newsFragment_to_newsDetailsFragment,bundleOf("article" to list[position]))
        }




    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}