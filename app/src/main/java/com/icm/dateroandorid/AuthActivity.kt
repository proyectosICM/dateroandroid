package com.icm.dateroandorid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.snackbar.Snackbar

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_auth)


        val btnValidateCode = findViewById<AppCompatButton>(R.id.btnValidateCode)
        val editTextVerificationCode = findViewById<AppCompatEditText>(R.id.editTextVerificationCode)
        btnValidateCode.setOnClickListener {

            val verificationCode = editTextVerificationCode.text.toString()

            if(verificationCode == "1234"){
                Snackbar.make(findViewById(R.id.root_layout_id), "Bienvenido al sistema", Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, PrincipalPanelActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(findViewById(R.id.root_layout_id), "Código de verificación incorrecto", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}