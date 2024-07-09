package com.sampath.androidTask.ui

import android.app.SearchManager
import android.content.Context
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
import com.sampath.androidTask.utils.suggestions
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
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        callApi()

        binding.swipeRefresh.setOnRefreshListener {
            callApi()
        }


        lifecycleScope.launchWhenStarted {
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
        dogBreedAdapter = DogBreedAdapter{
            val intent = Intent(this, ImageViewActivity::class.java)
                .apply {
                    putExtra(INTENT_DOG_NAME, it)
                }
            startActivity(intent)
        }

        binding.dogsRv.apply {
            adapter = dogBreedAdapter
            setHasFixedSize(false)
        }
    }

    fun callApi() {
        binding.swipeRefresh.isRefreshing = false
        binding.dogsRv.visibility = View.GONE

        binding.progressBar.visibility = View.VISIBLE
        homeScreenViewModel.getDogBreeds()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        val menuItem = menu?.findItem(R.id.actionSearch)
        searchView = menuItem?.actionView as SearchView

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                menuItem.collapseActionView()
                updateDataToAdapter(query)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                updateDataToAdapter(query = newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun doSearch(query: String) {
        updateDataToAdapter(query)
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }
    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doSearch(query)
                suggestions.saveRecentQuery(query, null)
            }
        }

    }

    private fun updateDataToAdapter(query: String?) {
        val filteredBreeds = if (query.isNullOrEmpty()) {
            listOfBreeds
        } else {
            listOfBreeds.filter { it.contains(query, true) }
        }
        dogBreedAdapter.updateData(filteredBreeds)
    }

}