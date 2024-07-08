package com.sampath.androidTask.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sampath.androidTask.data.model.Breed
import com.sampath.androidTask.data.model.BreedImage
import com.sampath.androidTask.data.repo.DogRepo
import com.sampath.androidTask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogRepo: DogRepo
) : ViewModel() {

    private var _dogBreed_list = MutableStateFlow<Resource<Breed>>(Resource.Empty())
    val dogBreed_list: Flow<Resource<Breed>> = _dogBreed_list


    private var _dogBreedImage_list = MutableStateFlow<Resource<BreedImage>>(Resource.Empty())
    val dogBreedImage_list: Flow<Resource<BreedImage>> = _dogBreedImage_list

    fun getAllBreeds() = viewModelScope.launch {
        _dogBreed_list.emit(Resource.Loading())
        Timber.d("fetching dog breeds list")
        _dogBreed_list.emit(dogRepo.listAllDogs())
    }

    fun getImageByBreed(breed: String) = viewModelScope.launch {
        _dogBreedImage_list.emit(Resource.Loading())
        Timber.d("Fetching image url of breed $breed")
        _dogBreedImage_list.emit(dogRepo.getImageByBreed(breed))
    }
}