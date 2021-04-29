package com.example.baitaptuan1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptuan1.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_sign_up.*


open class Login : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context
    private lateinit var bindingLogin: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide();



        bindingLogin = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        context = this@Login

        bindingLogin.btLogin.setOnClickListener {
            var email: String = bindingLogin.etEmailignUp.text.toString()
            var password: String = bindingLogin.etPasswordSignUp.text.toString()

            if (isEmpty(email)) {
                bindingLogin.etEmailignUp.setError("Enter your email, Please!!!");
            }
            if (isEmpty(password)) {
                bindingLogin.etPasswordSignUp.setError("Enter your password, Please!!!");
            }

            loginViewModel.getLoginDetails(context, email, password)!!.observe(this, Observer {
                if (it == null) {
                    Toast.makeText(this, "Not found", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Login complete", Toast.LENGTH_LONG).show()
                    val bundle = Bundle()
                    bundle.putString("email", it.Email)
                    bundle.putString("fullName", it.FullName)
                    bundle.putString("passWord", it.Password)
                    val intent = Intent(this, listRestaurant::class.java)
                    intent.putExtras(bundle)
                    //     intent.putExtra( "Email", email)
                    startActivity(intent)


                }

            })

        }
        bindingLogin.tvSignInSignUp.setOnClickListener {

            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        bindingLogin.backLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}