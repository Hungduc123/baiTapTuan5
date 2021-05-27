package com.example.baitaptuan1.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptuan1.ListRestaurantsActivity
import com.example.baitaptuan1.viewmodel.LoginViewModel
import com.example.baitaptuan1.R
import com.example.baitaptuan1.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    lateinit var loginViewModel: LoginViewModel
//  lateinit var context: Context
//    private lateinit var bindingLogin: ActivityLoginBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        binding.btLogin.setOnClickListener {
            var email: String = binding.etEmailignUp.text.toString()
            var password: String = binding.etPasswordSignUp.text.toString()

            if (TextUtils.isEmpty(email)) {
                etEmailignUp.setError("Enter your email, Please!!!");
            }
            if (TextUtils.isEmpty(password)) {
                etPasswordSignUp.setError("Enter your password, Please!!!");
            }

            context?.let { it -> loginViewModel.getLoginDetails(it, email, password) }!!.observe(viewLifecycleOwner, Observer {
                if (it == null) {
                    Toast.makeText(context, "Not found", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Login complete", Toast.LENGTH_LONG).show()
                    val bundle = Bundle()
                    bundle.putString("email", it.Email)
                    bundle.putString("fullName", it.FullName)
                    bundle.putString("passWord", it.Password)
                    //val intent = Intent(this, listRestaurant::class.java)
//                    intent.putExtras(bundle)
//                    //     intent.putExtra( "Email", email)
//                    startActivity(intent)
                    activity?.let{
                        val intent = Intent (it, ListRestaurantsActivity::class.java)
                        it.startActivity(intent)
                    }

                }
            })
        }

        binding.tvSignInSignUp.setOnClickListener {

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<SignUpFragment>(R.id.frag_container_view)
                addToBackStack(null)
            }
        }
        binding.backLogin.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<WelcomeFragment>(R.id.frag_container_view)
                addToBackStack(null)
            }
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}