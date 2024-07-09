package com.sampath.androidTask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sampath.androidTask.data.remote.model.DogBreedResponse
import com.sampath.androidTask.data.remote.model.DogBreedImageResponse
import com.sampath.androidTask.domain.model.DogBreed
import com.sampath.androidTask.domain.model.DogBreedImage
import com.sampath.androidTask.domain.model.DogSubBreed
import com.sampath.androidTask.domain.repository.DogBreedImageRepoDomain
import com.sampath.androidTask.domain.repository.DogBreedsRepoDomain
import com.sampath.androidTask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogBreedRepo: DogBreedsRepoDomain,
    private val dogBreedImageRepo: DogBreedImageRepoDomain
) : ViewModel() {

    private var _dogBreed_list = MutableStateFlow<Resource<List<DogBreed>>>(Resource.Empty())
    val dogBreed_list: Flow<Resource<List<DogBreed>>> = _dogBreed_list

    private var _dogBreedImage_list = MutableStateFlow<Resource<DogBreedImage>>(Resource.Empty())
    val dogBreedImage_list: Flow<Resource<DogBreedImage>> = _dogBreedImage_list

    private var _dogSubBreedImage_list = MutableStateFlow<Resource<DogBreedImage>>(Resource.Empty())
    val dogSubBreedImage_list: Flow<Resource<DogBreedImage>> = _dogSubBreedImage_list


    fun getDogBreeds() = viewModelScope.launch {
        _dogBreed_list.emit(Resource.Loading())
        Timber.d("Fetching all dog breeds...")
        _dogBreed_list.emit(dogBreedRepo.getDogBreeds())
    }

    fun getImageByBreed(breed: String) = viewModelScope.launch {
        _dogBreedImage_list.emit(Resource.Loading())
        Timber.d("Fetching image url of breed $breed")
        _dogBreedImage_list.emit(dogBreedImageRepo.getImageByBreed(breed))
    }
    fun getImageBySubBreed(breed: String, subBreed: String) = viewModelScope.launch {
        _dogSubBreedImage_list.emit(Resource.Loading())
        Timber.d("Fetching image url of Sub Breed $subBreed")
        _dogSubBreedImage_list.emit(dogBreedImageRepo.getImagedBySubBreed(breed, subBreed))
    }
}