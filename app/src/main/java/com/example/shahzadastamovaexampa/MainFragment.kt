package com.example.shahzadastamovaexampa

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shahzadastamovaexampa.api.Item
import com.example.shahzadastamovaexampa.api.Response
import com.example.shahzadastamovaexampa.database.OneCharacter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainFragment : Fragment(R.layout.fragment_main) {

    private val dbInstance get() = Injector.database
    private val rickMortyApi get() = Injector.rickMortyApi

    private lateinit var listener: OnClick
    private lateinit var swipe: SwipeRefreshLayout

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnClick
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe = view.findViewById(R.id.swipe)

        val adapter = CharactersAdapter {
            listener.openItem(it)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        rickMortyApi.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .map{
                itemToCharacter(it)
            }
            .map {
                dbInstance.charactersDao().insertList(it)
                it
            }
            .map {
                characterToItem(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                adapter.setData(it)
                Log.e("TAG", "fragmentMain doOnNext ${Thread.currentThread().name}")
                Log.e("TAG", "fragmentMain ${it}")
            }
            .doOnError {
                Log.e("TAG", "fragmentMain doOnError ${Thread.currentThread().name}")
            }
            .subscribe()


        swipe.setOnRefreshListener {
            rickMortyApi.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .map {
                    itemToCharacter(it)
                }
                .map {
                    dbInstance.charactersDao().insertList(it)
                    it
                }
                .map {
                    characterToItem(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    adapter.setData(it)
                }
                .doOnError {
                    Log.e("TAG", "fragmentMain Swipe doOnError ${Thread.currentThread().name}")
                }
                .subscribe()
            swipe.isRefreshing = false
        }
        adapter.notifyDataSetChanged()


    }

    private fun itemToCharacter(list: Response) : List<OneCharacter> {
        val listCh = mutableListOf<OneCharacter>()
         list.results.forEach{
            val character = OneCharacter(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                gender = it.gender,
                image = it.image,
                episode = it.episode.toString()
            )
            listCh.add(character)
        }
        return listCh.toList()
    }

    private fun characterToItem(list: List<OneCharacter>) : List<Item> {
        val listCh = mutableListOf<Item>()
        list.forEach{
            val item = Item(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                gender = it.gender,
                image = it.image,
                episode = null
            )
            listCh.add(item)
        }
        return listCh.toList()
    }
}