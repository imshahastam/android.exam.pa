package com.example.shahzadastamovaexampa

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

class InfoFragment : Fragment (R.layout.fragment_info) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactNameView = view.findViewById<AppCompatTextView>(R.id.nameInfoTxt)
        val contactPhoneView = view.findViewById<AppCompatTextView>(R.id.phoneInfoTxt)

        val contactName = arguments?.getString("nameKey")
        val contactPhone = arguments?.getString("phoneKey")

        contactNameView.text = contactName.toString()
        contactPhoneView.text = contactPhone.toString()

    }
}