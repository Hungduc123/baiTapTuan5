package com.example.baitaptuan1

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptuan1.fragment.SignUpFragment
import com.example.baitaptuan1.fragment.WelcomeFragment
import com.example.baitaptuan1.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    lateinit var loginViewModel: LoginViewModel
//  lateinit var context: Context
//    private lateinit var bindingLogin: ActivityLoginBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


     btLogin.setOnClickListener {
         var email: String = etEmailignUp.text.toString()
         var password: String = etPasswordSignUp.text.toString()

         if (TextUtils.isEmpty(email)) {
             etEmailignUp.setError("Enter your email, Please!!!");
         }
         if (TextUtils.isEmpty(password)) {
             etPasswordSignUp.setError("Enter your password, Please!!!");
         }

         context?.let { it1 -> loginViewModel.getLoginDetails(it1, email, password) }!!.observe(viewLifecycleOwner, Observer {
             if (it == null) {
                 Toast.makeText(context, "Not found", Toast.LENGTH_LONG).show()
             } else {
                 Toast.makeText(context, "Login complete", Toast.LENGTH_LONG).show()
//                 val bundle = Bundle()
//                 bundle.putString("email", it.Email)
//                 bundle.putString("fullName", it.FullName)
//                 bundle.putString("passWord", it.Password)
//                 val intent = Intent(this, listRestaurant::class.java)
//                 intent.putExtras(bundle)
//                 //     intent.putExtra( "Email", email)
//                 startActivity(intent)


             }

         })


     }


    tvSignInSignUp.setOnClickListener {

        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SignUpFragment>(R.id.frag_container_view)
            addToBackStack(null)
        }
        }
      backLogin.setOnClickListener {
          parentFragmentManager.commit {
              setReorderingAllowed(true)
              replace<WelcomeFragment>(R.id.frag_container_view)
              addToBackStack(null)
          }
        }




    }


}