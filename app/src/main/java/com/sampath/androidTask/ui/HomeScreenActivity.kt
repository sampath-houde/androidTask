package com.sampath.androidTask.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.sampath.androidTask.R
import com.sampath.androidTask.databinding.ActivityHomescreenBinding
import com.sampath.androidTask.ui.adapter.DogBreedAdapter
import com.sampath.androidTask.utils.Constants.INTENT_DOG_NAME
import com.sampath.androidTask.utils.Resource
import com.sampath.androidTask.utils.toastShort
import com.sampath.androidTask.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeScreenActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityHomescreenBinding::inflate)
    private lateinit var dogBreedAdapter: DogBreedAdapter
    private val homeScreenViewModel: DogViewModel by viewModels()
    private var listOfBreeds = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dogBreedAdapter = DogBreedAdapter{
            val intent = Intent(this, ImageViewActivity::class.java)
                .apply {
                    putExtra(INTENT_DOG_NAME, it)
                }
            startActivity(intent)
        }
        initRecyclerView()

        callApi()
        binding.swipeRefresh.setOnRefreshListener {
            callApi()
        }

        lifecycleScope.launch {
            homeScreenViewModel.dogBreed_list.collect { response ->

                when(response) {
                    is Resource.Empty -> Unit
                    is Resource.Error -> {
                        toastShort(response.message)
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Timber.d("Fetching Dog Breed List...")
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.dogsRv.visibility = View.VISIBLE

                        response.data?.forEach { item -> listOfBreeds.add(item.name) }
                        dogBreedAdapter.updateData(listOfBreeds)
                    }
                }

            }
        }
    }

    fun initRecyclerView() {
        binding.dogsRv.apply {
            adapter = dogBreedAdapter
            setHasFixedSize(false)
        }
    }

    fun callApi() {
        binding.swipeRefresh.isRefreshing = false
        binding.dogsRv.visibility = View.GONE

        binding.progressBar.visibility = View.VISIBLE
        homeScreenViewModel.getAllBreeds()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        val menuItem = menu?.findItem(R.id.actionSearch)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { query ->
                    val filteredList = listOfBreeds.filter { it.contains(query, ignoreCase = true) }
                    if(filteredList.isEmpty()) {
                        //TODO show no result
                    } else {
                        dogBreedAdapter.updateData(filteredList)
                    }

                }

                return false
            }

        })
        return super.onCreateOptionsMenu(menu)

    }
}