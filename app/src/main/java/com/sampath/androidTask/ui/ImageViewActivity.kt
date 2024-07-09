package com.sampath.androidTask.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.sampath.androidTask.R
import com.sampath.androidTask.databinding.ActivityImageViewBinding
import com.sampath.androidTask.ui.adapter.DogImageAdapter
import com.sampath.androidTask.utils.Constants.INTENT_DOG_NAME
import com.sampath.androidTask.utils.Resource
import com.sampath.androidTask.utils.getCircularProgress
import com.sampath.androidTask.utils.toastShort
import com.sampath.androidTask.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ImageViewActivity : AppCompatActivity() {

    val binding by viewBinding(ActivityImageViewBinding::inflate)
    private val homeScreenViewModel: DogViewModel by viewModels()

    @Inject
    lateinit var dogImageAdapter: DogImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentString = intent.getStringExtra(INTENT_DOG_NAME)
        initRecyclerView()

        if(intentString!!.contains("//")) {
            val breed = intentString.split("//")[0]
            val subBreed = intentString.split("//")[1]
            homeScreenViewModel.getImageBySubBreed(breed, subBreed)
            title = subBreed
        } else {
            title = intentString
            homeScreenViewModel.getImageByBreed(intentString)
        }

        lifecycleScope.launch {
            homeScreenViewModel.dogBreedImage_list.collect { response ->
                when (response) {
                    is Resource.Empty -> Unit
                    is Resource.Error -> { toastShort(response.message) }
                    is Resource.Loading -> { Timber.d("Fetching Dog Breed Image...") }
                    is Resource.Success -> {
                        val imageListUrl = response.data?.imageUrl
                        if(imageListUrl.isNullOrEmpty())
                            dogImageAdapter.updateData(emptyList())
                        else
                            dogImageAdapter.updateData(imageListUrl)

                    }
                }
            }
        }

    }

    override fun onBackPressed() {
        finish()
    }

    private fun initRecyclerView() {
        binding.dogImageRv.apply {
            setHasFixedSize(true)
            adapter = dogImageAdapter
            layoutManager = GridLayoutManager(this@ImageViewActivity, 2)
        }
    }
}