package com.example.kannada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_logpage.*
import kotlinx.android.synthetic.main.activity_signupage.*
import kotlinx.android.synthetic.main.activity_signupage.pass
import kotlinx.android.synthetic.main.activity_signupage.user

class logpage : AppCompatActivity() {

    var mAuth: FirebaseAuth?=null
    var mAuthListner: FirebaseAuth.AuthStateListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logpage)

        mAuth=FirebaseAuth.getInstance()
        mAuthListner=FirebaseAuth.AuthStateListener {  }
    }
    fun log(view: View){
      if(TextUtils.isEmpty(user.text.toString())){
          Toast.makeText(applicationContext,"Enter details",Toast.LENGTH_LONG).show()

      }
        else{
            mAuth!!.signInWithEmailAndPassword(user.text.toString(),pass.text.toString()).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(applicationContext,"Logged-in",Toast.LENGTH_LONG).show()
                    val intent= Intent(applicationContext,mainpage::class.java)
                    startActivity(intent)

                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage.toString(), Toast.LENGTH_LONG).show()

            }

        }

    }
}
