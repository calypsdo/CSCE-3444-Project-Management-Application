package com.example.fuji

import android.nfc.Tag
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.fuji.RegisterFragment.Companion.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

/**
 * A "simple" [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    // [START declare auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        val emailInputView = view.findViewById<EditText>(R.id.register_email_entry)
        val firstNameInputView = view.findViewById<EditText>(R.id.register_first_name_entry)
        val lastNameInputView = view.findViewById<EditText>(R.id.register_last_name_entry)
        val passwordInputView = view.findViewById<EditText>(R.id.register_password_entry)
        val confirmPasswordInputView = view.findViewById<EditText>(R.id.register_confirm_password_entry)

        view.findViewById<ImageView>(R.id.register_back_button).setOnClickListener {
            findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)
        }

        view.findViewById<Button>(R.id.register_button).setOnClickListener {

            val emailInput = emailInputView.text.toString().trim()
            val firstNameInput = firstNameInputView.text.toString().trim()
            val lastNameInput = lastNameInputView.text.toString().trim()
            val passwordInput = passwordInputView.text.toString().trim()
            val confirmPasswordInput = confirmPasswordInputView.text.toString().trim()

            if (firstNameInput.isEmpty() ||
                lastNameInput.isEmpty() ||
                passwordInput.isEmpty() ||
                confirmPasswordInput.isEmpty() ||
                emailInput.isEmpty()) {
                Toast.makeText(context, "Please fill all fields first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (passwordInput != confirmPasswordInput) {
                Toast.makeText(context, "Confirmation does not match password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (passwordInput.length < 6) {
                Toast.makeText(context, "Password must be greater than six characters", Toast.LENGTH_SHORT).show()
            }
            if (!emailInput.matches(emailPattern)) {
                Toast.makeText(context, "Invalid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            createAccount(emailInput, passwordInput)
            sendEmailVerification()
            Handler().postDelayed({findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)}, 3000)
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////START OF FIREBASE///////////////////////////////////////////

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }

    private fun createAccount(email: String, password: String){
        Log.d(TAG, "createAccount:$email")

        // [START create user with email
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
                val user = auth.currentUser
                updateUI(user)
            }
            else {
                // If sign in fails, display a message to the user
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
        // [END create user with email
    }

    private fun sendEmailVerification() {
        // [START send email verification]
        val user = auth.currentUser
        user.sendEmailVerification().addOnCompleteListener(requireActivity()) { task ->
            // Email Verification sent
            if(task.isSuccessful) {
                Toast.makeText(context, "Verification email sent", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "sendEmailVerification", task.exception)
                Toast.makeText(context, "Failed to send verification email", Toast.LENGTH_SHORT).show()
            }
        }
        // [END send email verification
    }

    private fun reload() {
        auth.currentUser!!.reload().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateUI(auth.currentUser)
                //Toast.makeText(context, "Reload successful!", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "reload", task.exception)
                Toast.makeText(context, "Failed to reload user", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
    }

    companion object {
         private const val TAG = "EmailPassword"
    }
    ///////////////////////////////////END OF FIREBASE/////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
}
