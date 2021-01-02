package gaur.himanshu.august.androidtestingcodelabs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.august.androidtestingcodelabs.R
import kotlinx.android.synthetic.main.fragment_login.view.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        view.login_button.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }


    }
    fun login(username:String,password:String,phone: String):Boolean{

        if(username.isEmpty()||password.isEmpty()||phone.isEmpty()){
            return false
        }
        if(username.length<=8){
            return false
        }
        if(password.length<=6){
            return false
        }
        if (phone.length<10){
            return false
        }
        return true

    }






}