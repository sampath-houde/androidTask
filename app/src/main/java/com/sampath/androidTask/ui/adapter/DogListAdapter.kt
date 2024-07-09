package com.sampath.androidTask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sampath.androidTask.R
import com.sampath.androidTask.databinding.DogItemBinding
import com.sampath.androidTask.domain.model.DogBreed
import javax.inject.Inject

class DogBreedAdapter(val context: Context, private val onTaskClicked: (String) -> Unit) :
    RecyclerView.Adapter<DogBreedAdapter.DogBreedViewHolder>() {

        private lateinit var dogSubBreedsAdapter: DogSubBreedsAdapter

    private var currentList = mutableListOf<DogBreed>()
        private val expandedBreeds = mutableSetOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedViewHolder {
        val binding =
            DogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogBreedViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateData(updatedList: List<DogBreed>) {
        val diffCallback = DogBreedDiffUtil(currentList, updatedList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        currentList.clear()
        currentList.addAll(updatedList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class DogBreedViewHolder(private val binding: DogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(breed: DogBreed) {
            binding.breedName.text = breed.name
            binding.root.setOnClickListener {
                onTaskClicked(breed.name)
            }

            if(breed.subBreed.isEmpty())
                binding.arrowImg.visibility = View.GONE
            else
                binding.arrowImg.visibility = View.VISIBLE

            binding.subBreedsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            dogSubBreedsAdapter = DogSubBreedsAdapter(context, breed.subBreed.toMutableList(), breed.name)
            binding.subBreedsRecyclerView.adapter = dogSubBreedsAdapter
            dogSubBreedsAdapter.updateData(breed.subBreed, breed.name)

            binding.arrowImg.setOnClickListener {
                if (expandedBreeds.contains(breed.name)) {
                    expandedBreeds.remove(breed.name)
                    binding.subBreedsRecyclerView.visibility = View.GONE
                    binding.arrowImg.setImageResource(R.drawable.baseline_arrow_drop_down_circle_24)
                } else {
                    expandedBreeds.add(breed.name)
                    binding.subBreedsRecyclerView.visibility = View.VISIBLE
                    binding.arrowImg.setImageResource(R.drawable.baseline_arrow_drop_up)
                }
            }

        }
    }
}