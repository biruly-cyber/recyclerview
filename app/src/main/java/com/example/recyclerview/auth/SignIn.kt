package com.example.recyclerview.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.MainActivity
import com.example.recyclerview.R
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class SignIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        //FOR REMOVING WINDOW DIALOG BOX
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

        auth = FirebaseAuth.getInstance()

        //logIn

        findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            logIn()
        }

//        //sign up
//        findViewById<Button>(R.id.btnSignUp).setOnClickListener {
//            signUp()
//        }
    }
    private fun logIn(){
        val name: EditText = findViewById(R.id.editTextTextPersonName2)
        val pass: EditText = findViewById(R.id.editTextTextPassword2)

        val setName = name.text.toString().trim()
        val setPass = pass.text.toString().trim()
        if(setName.isEmpty() || setPass.isEmpty()){
            Toast.makeText(this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show()
        }else{
            auth.signInWithEmailAndPassword(setName, setPass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "Sign In Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Invalid Email and Password",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

//    private fun signUp(){
//        startActivity(Intent(this, SignUp::class.java))
//        finish()
//    }
}