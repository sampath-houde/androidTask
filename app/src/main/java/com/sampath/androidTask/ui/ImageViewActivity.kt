package com.sampath.androidTask.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.sampath.androidTask.R
import com.sampath.androidTask.databinding.ActivityImageViewBinding
import com.sampath.androidTask.utils.Constants.INTENT_DOG_NAME
import com.sampath.androidTask.utils.Resource
import com.sampath.androidTask.utils.getCircularProgress
import com.sampath.androidTask.utils.toastShort
import com.sampath.androidTask.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ImageViewActivity : AppCompatActivity() {

    val binding by viewBinding(ActivityImageViewBinding::inflate)
    private val homeScreenViewModel: DogViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dogName = intent.getStringExtra(INTENT_DOG_NAME)



        homeScreenViewModel.getImageByBreed(dogName!!)
        lifecycleScope.launch {
            homeScreenViewModel.dogBreedImage_list.collect { response ->
                when (response) {
                    is Resource.Empty -> Unit
                    is Resource.Error -> {
                        toastShort(response.message)
                    }
                    is Resource.Loading -> {
                        Timber.d("Fetching Dog Breed Image...")
                    }
                    is Resource.Success -> {
                        val randomImageUrl = response.data?.imageUrl?.random()
                        Glide.with(this@ImageViewActivity)
                            .load(randomImageUrl)
                            .placeholder(getCircularProgress())
                            .into(binding.dogImageView)
                        binding.dogBreedName.text = dogName
                    }
                }
            }
        }

    }
}