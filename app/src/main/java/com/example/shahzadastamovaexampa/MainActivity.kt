package com.example.shahzadastamovaexampa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

//Shahzada Stamova
class MainActivity : AppCompatActivity(), OnClick {

    private val rickMortyApi get() = Injector.rickMortyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frg_container, MainFragment())
            .commit()
    }

    override fun openFragmentMain() {
        val fragment = MainFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frg_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openItem(id: Long?) {

        val fragment = ItemInfoFragment()
        val bundle = Bundle()
        if (id != null) {
            bundle.putLong("id", id)
        }

        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.frg_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}