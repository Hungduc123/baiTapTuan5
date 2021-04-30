package com.example.baitaptuan1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptuan1.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpFragment : Fragment() {
    private lateinit var bindingSignUp: ActivitySignUpBinding
    lateinit var loginViewModel: LoginViewModel

    lateinit var email: String
    lateinit var password: String
    lateinit var fullname: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            }
            else if (password.isEmpty()) {
                bindingSignUp.etPasswordSignup.error = "Please enter the password"
            } else if (!isPasswordValid(password)) {
                bindingSignUp.etPasswordSignup.error = "Please enter correct format"
            }
            else {
                context?.let { it1 -> loginViewModel.insertData(it1, email, password, fullname) }
                Toast.makeText(context, "Login complete", Toast.LENGTH_LONG).show()
//                   val intent = Intent(this,Login::class.java)
//                   startActivity(intent)
            }


    }
        bindingSignUp.tvSignInSignUp.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<LoginFragment>(R.id.frag_container_view)
                addToBackStack(null)
            }
        }
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