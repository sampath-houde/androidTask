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

class DogSubBreedsAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<DogSubBreedsAdapter.DogSubBreedViewHolder>() {

        private val subBreeds = mutableListOf<DogSubBreed>()
    private var breedName: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogSubBreedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return DogSubBreedViewHolder(view)
    }

    fun updateData(newList: List<DogSubBreed>, breedName: String) {
        subBreeds.clear()
        subBreeds.addAll(newList)
        this.breedName = breedName
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DogSubBreedViewHolder, position: Int) {
        holder.subBreedName.text = subBreeds[position].name.uppercase()
        holder.subBreedName.setOnClickListener {
            context.startActivity(
                Intent(context, ImageViewActivity::class.java)
                    .putExtra(INTENT_DOG_NAME, "$breedName//${subBreeds[position].name}")
            )
        }
    }

    override fun getItemCount() = subBreeds.size

    class DogSubBreedViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val subBreedName: TextView = itemView.findViewById(android.R.id.text1)
    }
}