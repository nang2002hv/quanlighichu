package com.example.ontapandroid.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ontapandroid.R
import com.example.ontapandroid.databinding.ActivityUpdateNoteBinding
import com.example.ontapandroid.model.Note
import com.example.ontapandroid.viewmodel.NoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateNoteActivity : AppCompatActivity() {

    // lazy chỉ dùng cho val , lateinit ngược lại
    private  val noteViewModel : NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModel.NoteViewModelFactory(application)).get(NoteViewModel::class.java)
    }

    private lateinit var binding: ActivityUpdateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        var id = intent.getIntExtra("UPDATE_NOTE_ID", 0)
        var note : Note? = null
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                note = noteViewModel.getNoteById(id).first()
            }
            binding.edtNoteTitle.setText(note?.title)
            binding.edtNoteDes.setText(note?.description)
        }
        binding.btnUpdate.setOnClickListener {
            note?.title = binding.edtNoteTitle.text.toString()
            note?.description = binding.edtNoteDes.text.toString()
            lifecycleScope.launch {
                withContext(Dispatchers.Main){
                    note?.let { noteViewModel.update(it) }
                }

            }
            var intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}