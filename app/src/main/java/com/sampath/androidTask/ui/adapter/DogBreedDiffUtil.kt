package com.sampath.androidTask.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sampath.androidTask.domain.model.DogBreed

class DogBreedDiffUtil(oldList: List<DogBreed>, newList: List<DogBreed>) : DiffUtil.Callback() {
    private val mOldNoteList: List<DogBreed> = oldList
    private val mNewNoteList: List<DogBreed> = newList

    override fun getOldListSize(): Int = mOldNoteList.size

    override fun getNewListSize(): Int = mNewNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition] == mNewNoteList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = mOldNoteList[oldItemPosition];
        val newNote = mNewNoteList[newItemPosition];

        return oldNote == newNote
    }

}
