package com.example.shahzadastamovaexampa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

//Shahzada Stamova
class MainActivity : AppCompatActivity(), EnterData {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frg_container, MainFragment())
            .commit()
    }

    override fun enterData(contact: Contact) {
        val infoFragment = InfoFragment()
        val bundle = Bundle()
        bundle.putString("nameKey", contact.name)
        bundle.putString("phoneKey", contact.phone)
        infoFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.frg_container, infoFragment)
            .addToBackStack(null)
            .commit()
    }


}