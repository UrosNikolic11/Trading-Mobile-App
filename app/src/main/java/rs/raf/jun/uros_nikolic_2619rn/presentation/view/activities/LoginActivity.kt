package rs.raf.jun.uros_nikolic_2619rn.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import org.koin.android.ext.android.inject
import org.koin.experimental.property.inject
import rs.raf.jun.uros_nikolic_2619rn.R
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.composable.LoginLayout
import timber.log.Timber

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val composeHolder = findViewById<ComposeView>(R.id.composeHolder)
        composeHolder.setContent {
            LoginLayout(onClick = ::login)
        }
    }

    private fun login(user: String,email: String, pin: String){
        if(user != "" && pin != "" && validEmail(email)){
            if(pin.equals("12345")){
                val pref: SharedPreferences by inject()
                pref.edit().putString("userKey", user).apply()
                pref.edit().putString("emailKey", email).apply()
                intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else Toast.makeText(this, "Pogresna lozinka", Toast.LENGTH_SHORT).show()
        }
        else Toast.makeText(this, "Niste lepo uneli sve podatke", Toast.LENGTH_SHORT).show()
    }

    private fun validEmail(email: String): Boolean{
        if(email == "") return false
        if(email.contains("@")){
            return true
        }
        else  Toast.makeText(this, "Invalid email addres", Toast.LENGTH_SHORT).show()
        return false
    }
}