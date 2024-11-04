package com.example.mybank.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mybank.RegisterActivity
import com.example.mybank.R
import com.example.mybank.StartViewData

class StartFragment : Fragment() {

    private lateinit var startImageIV: ImageView
    private lateinit var startTextTV: TextView
    private lateinit var startBTN: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startImageIV = view.findViewById(R.id.startImageIV)
        startTextTV = view.findViewById(R.id.startTextTV)
        startBTN = view.findViewById(R.id.startBTN)
        val startList = arguments?.getSerializable("start") as StartViewData
        startTextTV.text = startList.startText
        startImageIV.setImageResource(startList.image)
        if (startList.check) {
            startBTN.visibility = View.VISIBLE
            startBTN.setOnClickListener {
                startActivity(Intent(activity, RegisterActivity::class.java))
            }
        }
    }
}