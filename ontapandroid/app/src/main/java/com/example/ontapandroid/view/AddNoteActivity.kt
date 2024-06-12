package com.example.ontapandroid.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ontapandroid.R
import com.example.ontapandroid.databinding.ActivityAddNoteBinding
import com.example.ontapandroid.model.Note
import com.example.ontapandroid.viewmodel.NoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNoteActivity : AppCompatActivity() {

    private  val noteViewModel : NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModel.NoteViewModelFactory(application)).get(NoteViewModel::class.java)
    }

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.btnAdd.setOnClickListener {
            var note : Note = Note(binding.edtNoteTitle.text.toString(), binding.edtNoteDes.text.toString())
            lifecycleScope.launch {
                withContext(Dispatchers.Main){
                    noteViewModel.insert(note)
                }
            }
            var intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}