package com.example.baitaptuan1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
//import androidx.fragment.app.commit
//import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptuan1.viewmodel.LoginViewModel
import com.example.baitaptuan1.R
import com.example.baitaptuan1.databinding.FragmentSignUpBinding
import java.util.regex.Pattern

class SignUpFragment : Fragment() {
    var bindingSignUp: FragmentSignUpBinding?=null
    lateinit var loginViewModel: LoginViewModel

    lateinit var email: String
    lateinit var password: String
    lateinit var fullname: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindingSignUp:FragmentSignUpBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sign_up,container,false)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        bindingSignUp.btLogin.setOnClickListener {
            email = bindingSignUp.etEmailSignUp.text.toString().trim()
            password = bindingSignUp.etPasswordSignup.text.toString().trim()
            fullname = bindingSignUp.etFullNameSignUp.text.toString().trim()
            if (fullname.isEmpty()) {
                bindingSignUp.etFullNameSignUp.error = "Please enter the fullname"
            } else if (email.isEmpty() && !isEmailValid(email)) {
                bindingSignUp.etEmailSignUp.error = "Please enter the email"
            }else if ( !isEmailValid(email)) {
                bindingSignUp.etEmailSignUp.error = "Please enter correct format"
            } else if (password.isEmpty()) {
                bindingSignUp.etPasswordSignup.error = "Please enter the password"
            } else if (!isPasswordValid(password)) {
                bindingSignUp.etPasswordSignup.error = "Please enter correct format"
            } else {
                context?.let { it -> loginViewModel.insertData(it, email, password, fullname) }
                Toast.makeText(context, "Login complete", Toast.LENGTH_LONG).show()
            }
        }

        bindingSignUp.tvSignInSignUp.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<LoginFragment>(R.id.frag_container_view)
                addToBackStack(null)
            }
        }

        return bindingSignUp.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
    fun isPasswordValid(password: String): Boolean {
        return Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!\\-_?&])(?=\\S+$).{8,}"
        ).matcher(password).matches()
    }
}