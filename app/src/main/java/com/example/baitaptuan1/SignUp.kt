package com.example.baitaptuan1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptuan1.databinding.ActivitySignUpBinding
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern

class SignUp : AppCompatActivity() {
    private lateinit var bindingSignUp: ActivitySignUpBinding
    lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context
    lateinit var email: String
    lateinit var password: String
    lateinit var fullname: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide();


        bindingSignUp = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)


        context = this@SignUp

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
                loginViewModel.insertData(context, email, password, fullname)
                Toast.makeText(this, "Login complete", Toast.LENGTH_LONG).show()
//                   val intent = Intent(this,Login::class.java)
//                   startActivity(intent)
            }

        }



        bindingSignUp.tvSignInSignUp.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
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