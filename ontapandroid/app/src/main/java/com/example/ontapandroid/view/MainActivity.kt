package com.example.ontapandroid.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ontapandroid.R
import com.example.ontapandroid.adapter.NoteAdapter
import com.example.ontapandroid.databinding.ActivityMainBinding
import com.example.ontapandroid.model.Note
import com.example.ontapandroid.viewmodel.NoteViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    // lazy chỉ dùng cho val , lateinit ngược lại
    private  val noteViewModel : NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModel.NoteViewModelFactory(application)).get(NoteViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initControls()
        initEvents()

    }



    fun initEvents(){
        binding.add.setOnClickListener {
            val intent  : Intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    fun initControls(){
        val adapter = NoteAdapter(mutableListOf(), onItemClick, onItemDelete)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch {
            noteViewModel.allNotes.collect { notes ->
                adapter.submitData(notes)
            }
        }

    }

    private val onItemClick : (Note) -> Unit = {
        val intent  = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE_ID", it.id)
        startActivity(intent)
    }

    private val onItemDelete : (Note) -> Unit = {
        noteViewModel.delete(it)
    }
}