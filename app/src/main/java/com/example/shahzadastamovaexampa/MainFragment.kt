package com.example.shahzadastamovaexampa

import android.content.Context
import android.graphics.Insets.add
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var listener: EnterData

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as EnterData
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText = view.findViewById<AppCompatEditText>(R.id.nameEditText)
        val phoneEditText = view.findViewById<AppCompatEditText>(R.id.phoneEditText)
        val btnPlus = view.findViewById<AppCompatButton>(R.id.btn_plus)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = AdapterRec{
            listener.enterData(it)
        }


        btnPlus.setOnClickListener{
            val newContact = Contact(nameEditText.text.toString(), phoneEditText.text.toString())
            adapter.setData(newContact)
        }


        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
    }

}