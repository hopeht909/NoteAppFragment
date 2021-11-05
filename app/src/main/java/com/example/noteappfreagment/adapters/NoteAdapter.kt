package com.example.noteappfreagment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappfreagment.FragmentHome
import com.example.noteappfreagment.R
import com.example.noteappfreagment.data.Note
import com.example.noteappfreagment.databinding.NoteRowBinding


class NoteAdapter(private val listFragment: FragmentHome): RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {
    private var notes = emptyList<Note>()

    class ItemViewHolder(val binding: NoteRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ItemViewHolder {
        return ItemViewHolder(
            NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note = notes[position]

        holder.binding.apply {
            tvNote.text = note.noteText
            ibEditNote.setOnClickListener {

                with(listFragment.sharedPreferences.edit()) {
                    putString("NoteId", note.id)
                    apply()
                }
                listFragment.findNavController().navigate(R.id.action_fragmentHome_to_fragmentUpdate)
            }
            ibDeleteNote.setOnClickListener {
                listFragment.listViewModel.deleteNote(note.id)
            }
        }
    }

    override fun getItemCount() = notes.size

    fun update(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}