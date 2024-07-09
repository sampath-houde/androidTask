package com.sampath.androidTask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sampath.androidTask.databinding.DogImageItemBinding
import com.sampath.androidTask.utils.getCircularProgress
import javax.inject.Inject

class DogImageAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<DogImageAdapter.DogBreedImageViewHolder>() {
    private var currentList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedImageViewHolder {
        val binding = DogImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogBreedImageViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: DogImageAdapter.DogBreedImageViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateData(updatedList: List<String>) {
        val diffCallback = DogBreedDiffUtil(currentList, updatedList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        currentList.clear()
        currentList.addAll(updatedList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class DogBreedImageViewHolder(private val binding: DogImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            Glide.with(context)
                .load(imageUrl)
                .placeholder(context.getCircularProgress())
                .into(binding.dogImage)
        }
    }
}