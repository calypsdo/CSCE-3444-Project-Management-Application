package com.example.fuji

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import java.util.*

/**
 * A "simple" [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        val emailInput = view.findViewById<EditText>(R.id.register_email_entry).text
        val firstNameInput = view.findViewById<EditText>(R.id.register_first_name_entry).text
        val lastNameInput = view.findViewById<EditText>(R.id.register_last_name_entry).text
        val passwordInput = view.findViewById<EditText>(R.id.register_password_entry).text
        val confirmPasswordInput = view.findViewById<EditText>(R.id.register_confirm_password_entry).text

        view.findViewById<Button>(R.id.register_button).setOnClickListener {

            if(firstNameInput.toString().trim().isEmpty()) {
                Toast.makeText(context, "Enter first name", Toast.LENGTH_SHORT).show()
            } else {
                if(lastNameInput.toString().trim().isEmpty()) {
                    Toast.makeText(context, "Enter last name", Toast.LENGTH_SHORT).show()
                } else {
                    if(passwordInput.toString().trim().isEmpty()) {
                        Toast.makeText(context, "Enter a password", Toast.LENGTH_SHORT).show()
                    } else {
                        if(confirmPasswordInput.toString().trim().isEmpty()) {
                            Toast.makeText(context, "Enter a password confirmation", Toast.LENGTH_SHORT).show()
                        } else {
                            if(passwordInput.toString().trim() != confirmPasswordInput.toString().trim()) {
                                Toast.makeText(context, "Confirmation does not match password", Toast.LENGTH_SHORT).show()
                            } else {
                                if(emailInput.toString().trim().isEmpty()) {
                                    Toast.makeText(context, "Enter email address", Toast.LENGTH_SHORT).show()
                                } else {
                                    if(emailInput.toString().matches(emailPattern)) {
                                        Toast.makeText(context, "Valid email address", Toast.LENGTH_SHORT).show()
                                        Handler().postDelayed({findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)}, 2000)
                                    } else {
                                        Toast.makeText(context, "Invalid email address", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }



}

//BUTTON/XML item code for actions
//val button = view.findViewById<Button>(R.id.register_button)
//button.setOnClickListener {  }

//val textview = findViewById<TextView>(R.id.textview)
//textview.setOnClickListener(clickListener)

//val button = findViewById<Button>(R.id.button)
//button.setOnClickListener(clickListener)