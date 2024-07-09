package com.sampath.androidTask.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sampath.androidTask.domain.model.DogSubBreed
import com.sampath.androidTask.ui.ImageViewActivity
import com.sampath.androidTask.utils.Constants.INTENT_DOG_NAME
import javax.inject.Inject

class DogSubBreedsAdapter @Inject constructor(val context: Context, var dogSubBreeds: MutableList<DogSubBreed>, var breedName: String) :
    RecyclerView.Adapter<DogSubBreedsAdapter.DogSubBreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogSubBreedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return DogSubBreedViewHolder(view)
    }

    fun updateData(newList: List<DogSubBreed>, breedName: String) {
        dogSubBreeds.clear()
        dogSubBreeds.addAll(newList)
        this.breedName = breedName
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DogSubBreedViewHolder, position: Int) {
        holder.subBreedName.text = dogSubBreeds[position].name.uppercase()
        holder.subBreedName.setOnClickListener {
            context.startActivity(
                Intent(context, ImageViewActivity::class.java)
                    .putExtra(INTENT_DOG_NAME, "$breedName//${dogSubBreeds[position].name}")
            )
        }
    }

    override fun getItemCount() = dogSubBreeds.size

    class DogSubBreedViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val subBreedName: TextView = itemView.findViewById(android.R.id.text1)
    }
}