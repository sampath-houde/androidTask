package com.sampath.androidTask.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import timber.log.Timber

class DogBreedDiffUtil(oldList: List<String>, newList: List<String>) : DiffUtil.Callback() {
    private val mOldNoteList: List<String> = oldList
    private val mNewNoteList: List<String> = newList

    override fun getOldListSize(): Int = mOldNoteList.size

    override fun getNewListSize(): Int = mNewNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList.get(oldItemPosition) == mNewNoteList.get(
            newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = mOldNoteList[oldItemPosition];
        val newNote = mNewNoteList[newItemPosition];

        return oldNote == newNote
    }

}
