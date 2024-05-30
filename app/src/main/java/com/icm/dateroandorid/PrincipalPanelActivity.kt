package com.icm.dateroandorid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ViewSwitcher

class PrincipalPanelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_panel)

        val viewSwitcher = findViewById<ViewSwitcher>(R.id.viewSwitcher)
        val btnPanel1 = findViewById<Button>(R.id.btnPanel1)
        val btnPanel2 = findViewById<Button>(R.id.btnPanel2)

        viewSwitcher.displayedChild = 0

        btnPanel1.setOnClickListener {
            viewSwitcher.displayedChild = 0
        }

        btnPanel2.setOnClickListener {
            viewSwitcher.displayedChild = 1
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