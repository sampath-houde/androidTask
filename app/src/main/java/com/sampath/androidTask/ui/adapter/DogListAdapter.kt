package com.sampath.androidTask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sampath.androidTask.databinding.DogItemBinding

class DogBreedAdapter(private val onTaskClicked: (String) -> Unit) :
    RecyclerView.Adapter<DogBreedAdapter.DogBreedViewHolder>() {
        private var currentList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedViewHolder {
        val binding =
            DogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogBreedViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        holder.bind(currentList.get(position))
    }

    fun updateData(updatedList: List<String>) {
        val diffCallback = DogBreedDiffUtil(currentList, updatedList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        currentList.clear()
        currentList.addAll(updatedList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class DogBreedViewHolder(private val binding: DogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(breed: String) {
            binding.breedName.text = breed
            binding.root.setOnClickListener {
                onTaskClicked(breed)
            }
        }
    }
}