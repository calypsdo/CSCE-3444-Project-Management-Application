package com.example.fuji

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    // [START declare auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth

        val emailInput = view.findViewById<EditText>(R.id.login_email_entry).text
        val passwordInput = view.findViewById<EditText>(R.id.login_password_entry).text

        view.findViewById<TextView>(R.id.register_text).setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
        }

        view.findViewById<Button>(R.id.login_button).setOnClickListener {

            if (emailInput.toString().trim().isEmpty() ||
                passwordInput.toString().trim().isEmpty()) {
                Toast.makeText(context, "Please fill all fields first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            signIn(emailInput.toString().trim(), passwordInput.toString().trim())
            //val intent = Intent(view.context, BoardsActivity::class.java)
            //startActivity(intent)
        }
    }

    //////////////////////////////START OF FIREBASE////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }

    private fun signIn(email: String, password: String) {
        // [START sign in with email]
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
                val intent = Intent(context, BoardsActivity::class.java)
                startActivity(intent)
            }
            else {
                // If sign in fails, display a message to the suer
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(context, "Email or Password Incorrect", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
        // [END sign in with email]
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

    ///////////////////////////////END OF FIREBASE/////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
}