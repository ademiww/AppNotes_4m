package com.example.appnotes_4m.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appnotes_4m.models.Notes
import com.example.repeatnavigation.R
import com.example.repeatnavigation.databinding.ItemNoteBinding

class NotesAdapter(private val notes: ArrayList<Notes>) : ListAdapter<Notes,NotesAdapter.NotesViewHolder>(DiffCallback()) {

    private val setBackground = listOf (
        R.drawable.bg_red,
        R.drawable.bg_yellow,
        R.drawable.bg_green
    )


    fun setData(notes: List<Notes>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(private val binding : ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Notes) {
            binding.tvTitle.text = note.title

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding: ItemNoteBinding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        val background:Int = setBackground[position % setBackground.size]
        holder.itemView.setBackgroundResource(background)
        holder.bind(notes.get(position))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class DiffCallback : DiffUtil.ItemCallback<Notes>(){
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.title == newItem.title
        }

    }
}