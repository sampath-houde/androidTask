package com.sampath.androidTask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sampath.androidTask.domain.model.DogSubBreed

class DogSubBreedsAdapter(private val subBreeds: List<DogSubBreed>) :
    RecyclerView.Adapter<DogSubBreedsAdapter.DogSubBreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogSubBreedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return DogSubBreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogSubBreedViewHolder, position: Int) {
        holder.subBreedName.text = subBreeds[position].name.uppercase()
    }

    override fun getItemCount() = subBreeds.size

    class DogSubBreedViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val subBreedName: TextView = itemView.findViewById(android.R.id.text1)
    }
}