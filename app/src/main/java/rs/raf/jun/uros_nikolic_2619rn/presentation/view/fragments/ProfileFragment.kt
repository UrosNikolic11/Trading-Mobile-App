package rs.raf.jun.uros_nikolic_2619rn.presentation.view.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import rs.raf.jun.uros_nikolic_2619rn.R
import rs.raf.jun.uros_nikolic_2619rn.databinding.FragmentProfileBinding
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.activities.LoginActivity
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.activities.MainActivity
import timber.log.Timber

class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    val userKey = "userKey"
    val emailKey = "emailKey"
    private lateinit var user: TextView
    private lateinit var email: TextView
    private lateinit var log: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val pref: SharedPreferences by inject()
        val username: String? = pref.getString(userKey, null)
        val addres: String? = pref.getString(emailKey, null)

        Timber.e(addres + "adresa")

        user = binding.profileUser
        email = binding.profileemail
        log = binding.logout

        user.setText(username)
        email.setText(addres)

        log.setOnClickListener{
            val pref: SharedPreferences by inject()
            pref.edit().clear().apply()
            val pref2: SharedPreferences by inject()
            pref2.edit().clear().apply()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

}