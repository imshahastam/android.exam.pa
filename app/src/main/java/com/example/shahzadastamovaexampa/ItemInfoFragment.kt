package com.example.shahzadastamovaexampa

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.shahzadastamovaexampa.api.Item
import com.example.shahzadastamovaexampa.api.Response
import com.example.shahzadastamovaexampa.database.OneCharacter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ItemInfoFragment : Fragment(R.layout.fragment_item_info) {

    private val rickMortyApi get() = Injector.rickMortyApi
    private lateinit var listener: OnClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnClick
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = view.findViewById<AppCompatTextView>(R.id.nameTxtInfo)
        val status = view.findViewById<AppCompatTextView>(R.id.statusTxtInfo)
        val location = view.findViewById<AppCompatTextView>(R.id.locationTxtInfo)
        val species = view.findViewById<AppCompatTextView>(R.id.speciesTxtInfo)
        val image = view.findViewById<ImageView>(R.id.imgInfo)

        val id = arguments?.getLong("id") ?: 1L
            rickMortyApi.getCharacterById(id)
                .subscribeOn(Schedulers.io())
                .map { it }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    name.text = it.name
                    status.text = it.status
                    species.text = it.species

                    Glide.with(requireContext())
                        .load(it.image)
                        .into(image)

                    Log.e(
                        "TAG",
                        "fragmentItemInfo doOnSuccess getById ${Thread.currentThread().name}"
                    )
                }
                .doOnError {
                    Log.e("TAG", "fragmentItemInfo doOnError getById ${Thread.currentThread().name}")
                }
                .subscribe()
        }

    }
