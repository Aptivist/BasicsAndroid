package com.aptivist.basicscourse

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.aptivist.basicscourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var personaDataSource : PersonaDataSource
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = MainActivityViewModel()
        binding.lifecycleOwner = this
        setContentView(binding.root)

        setClickListeners()
        registerObservers()
        registerUserInputs()
    }

    private fun registerUserInputs() {
        binding.etNombre.doOnTextChanged { newtext, _, _, size ->
            binding.etNombre.setSelection(size)
            viewModel.setNewName(newtext.toString())
        }

        binding.etApellido.doOnTextChanged { newtext, _, _, size ->
            binding.etApellido.setSelection(size)
            viewModel.setNewSurname(newtext.toString())

        }
    }

    private fun registerObservers() {
        viewModel.pName.observe(this){
            binding.etNombre.setText(it)
        }
        viewModel.pSurname.observe(this){ apellido ->
            binding.etApellido.setText(apellido)
        }
    }

    private fun setClickListeners() {
        binding.buttonGet.setOnClickListener {
            viewModel.getPerson()
        }
        binding.buttonSave.setOnClickListener {
            viewModel.savePerson()
        }
        binding.buttonClear.setOnClickListener {
            viewModel.clearScreen()
        }
    }


}