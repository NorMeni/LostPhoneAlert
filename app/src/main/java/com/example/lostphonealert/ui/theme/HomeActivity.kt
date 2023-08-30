package com.example.lostphonealert.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lostphonealert.ui.theme.ui.theme.LostPhoneAlertTheme
import com.example.lostphonealert.R
import android.app.Activity
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.view.View
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home);

        val popupButton = findViewById<Button>(R.id.popupButton) // Reference the popupButton from activity_home.xml

        val popupView = layoutInflater.inflate(R.layout.popup_layout, null)
        val popupWindow = PopupWindow(popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        popupView.findViewById<Button>(R.id.closeButton).setOnClickListener {
            popupWindow.dismiss()
        }

        popupButton.setOnClickListener {
            popupWindow.showAsDropDown(popupButton, 0, 0)
        }

    }

    fun goToMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun goToAboutPage(view: View) {
        val intent = Intent(this, AboutPage::class.java)
        startActivity(intent)
    }
}