package com.example.mybank.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mybank.IdGenerator
import com.example.mybank.R
import com.example.mybank.db.DBHelper
import com.example.mybank.db.Users
import kotlin.math.log

class RegisterFragment : Fragment() {

    private lateinit var loginET: EditText
    private lateinit var passwordET: EditText
    private lateinit var registerBTN: Button
    private lateinit var enterBTN: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DBHelper(requireContext(), null)
        loginET = view.findViewById(R.id.loginET)
        passwordET = view.findViewById(R.id.passwordET)
        registerBTN = view.findViewById(R.id.registerBTN)
        enterBTN = view.findViewById(R.id.enterBTN)

        registerBTN.setOnClickListener {
            if (loginET.text.isEmpty() || passwordET.text.isEmpty()) {
                Toast.makeText(activity, "Заполните оба поля", Toast.LENGTH_LONG).show()
            } else {
                val id = IdGenerator(db).addId()
                val login = loginET.text.toString()
                val pass = passwordET.text.toString()
                if (db.checkLogin(login)) {
                    Toast.makeText(
                        activity,
                        "Логин уже существует, введите другой",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val user = Users(Integer.parseInt(id), login, pass)
                    db.addUser(user)
                    Toast.makeText(
                        activity,
                        "Вы успешно зарегистрированы",
                        Toast.LENGTH_LONG
                    ).show()
                    requireFragmentManager().beginTransaction()
                        .add(R.id.register, LogInFragment())
                        .commit()
                }
            }
        }
        enterBTN.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .add(R.id.register, LogInFragment())
                .commit()
        }
    }
}