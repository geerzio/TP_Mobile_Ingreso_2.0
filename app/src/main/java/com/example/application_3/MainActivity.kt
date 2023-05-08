package com.example.application_3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.application_3.databinding.ActivityMainBinding

class  MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding = ActivityMainBinding.inflate(layoutInflater)

        mainActivityViewModel.viewState.observe(this){viewState ->
            handeViewState(viewState)
        }

        binding.name.addTextChangedListener{
            mainActivityViewModel.validateName(binding.name.text.toString())
        }
        binding.name.addTextChangedListener{
            mainActivityViewModel.validateEmail(binding.email.text.toString())
        }

        mainActivityViewModel.viewState.observe(this){
            viewState -> handeViewState(viewState)
        }

    }

    private fun handeViewState(viewState: MainActivityViewState){
        when(viewState){

            is MainActivityViewState.FieldErrorName -> {binding.name.error =
                " Debe colocar un nombre / mas de 2 caracteres "}
            is MainActivityViewState.FieldErrorEmail -> {binding.email.error =
                " Debe colocar un email, mayor a 5 caracteres  "}
            is MainActivityViewState.FieldErrorDni -> {binding.dni.error =
                "El DNI debe tener 8 caracteres"}

            is MainActivityViewState.SuccessName -> {binding.name.error = null}
            is MainActivityViewState.SuccessEmail -> {binding.email.error = null}
            is MainActivityViewState.SuccessDni -> {binding.dni.error = null}

            is MainActivityViewState.FieldErrorBtn -> {
                binding.btnLogIn.isEnabled = false
            }
            is MainActivityViewState.SuccessBtn -> {
                binding.btnLogIn.isEnabled = true
            }
        }
    }


}