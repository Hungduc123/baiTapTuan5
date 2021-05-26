package com.example.baitaptuan1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.baitaptuan1.R
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvSigninWelcome.setOnClickListener {

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<LoginFragment>(R.id.frag_container_view)
                addToBackStack(null)
            }
        }
        imgStartEmailPhone.setOnClickListener {

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<SignUpFragment>(R.id.frag_container_view)
                addToBackStack(null)
            }
        }
        tvStartEmailPhone.setOnClickListener {

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<SignUpFragment>(R.id.frag_container_view)
                addToBackStack(null)
            }
        }

    }



}