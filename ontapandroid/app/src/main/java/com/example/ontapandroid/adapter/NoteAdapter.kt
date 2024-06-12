package com.example.ontapandroid.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ontapandroid.databinding.ItemBinding
import com.example.ontapandroid.model.Note

class NoteAdapter(
    private var noteList : MutableList<Note>,
    private val onNoteClick : (Note) -> Unit,
    private val onNoteDetele : (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(val binding : ItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note : Note){
            binding.apply {
                tvTitle.text = note.title
                tvDescription.text = note.description
                btnDelete.setOnClickListener {
                    onNoteDetele(note)
                }
                //itemView đại diện cho một View mà RecyclerView.ViewHolder. đang giữa
                itemView.setOnClickListener {
                    onNoteClick(note)
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }


    @SuppressLint("NotifyDataSetChanged")
    fun submitData(newList : List<Note>){
        noteList = newList.toMutableList()
        notifyDataSetChanged()
    }

}