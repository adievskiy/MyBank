package com.example.mybank.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mybank.BankActivity
import com.example.mybank.R
import com.example.mybank.db.DBHelper


class LogInFragment : Fragment() {

    private lateinit var loginEnterET: EditText
    private lateinit var passwordEnterET: EditText
    private lateinit var loginBTN: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DBHelper(requireContext(), null)
        loginEnterET = view.findViewById(R.id.loginEnterET)
        passwordEnterET = view.findViewById(R.id.passwordEnterET)
        loginBTN = view.findViewById(R.id.loginBTN)

        loginBTN.setOnClickListener {
            if (loginEnterET.text.isEmpty() || passwordEnterET.text.isEmpty()) {
                Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_LONG).show()
            } else {
                val login = loginEnterET.text.toString()
                val pass = passwordEnterET.text.toString()
                if (db.checkUser(login, pass)) {
                    Toast.makeText(activity, "Успешный вход", Toast.LENGTH_LONG).show()
                    startActivity(Intent(activity, BankActivity::class.java))
                } else {
                    Toast.makeText(activity, "Неверные данные", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}