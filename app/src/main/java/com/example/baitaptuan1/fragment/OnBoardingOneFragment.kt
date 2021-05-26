package com.example.baitaptuan1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.baitaptuan1.R
import kotlinx.android.synthetic.main.fragment_on_boarding_one.*


class OnBoardingOneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding_one, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgNextOnboaringOne.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<OnBoardingTwoFragment>(R.id.frag_container_view)
                addToBackStack(null)
            }
        }
    }


}