package com.example.application_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.application_3.databinding.ActivityMainBinding
import com.example.application_3.model.User
import com.example.application_3.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding = ActivityMainBinding.inflate(layoutInflater)

        mainActivityViewModel.viewState.observe(this) { viewState ->
            handeViewState(viewState)
        }

        binding.name.addTextChangedListener {
            mainActivityViewModel.validateName(binding.name.text.toString())
        }
        binding.name.addTextChangedListener {
            mainActivityViewModel.validateEmail(binding.email.text.toString())
        }
        binding.dni.addTextChangedListener {
            mainActivityViewModel.validateDni(binding.dni.text.toString())
        }

        mainActivityViewModel.viewState.observe(this) { viewState ->
            handeViewState(viewState)
        }
        binding.btnAddUser.setOnClickListener {
            insertDataToDatabase()
        }

    }

    private fun handeViewState(viewState: MainActivityViewState) {
        when (viewState) {

            is MainActivityViewState.FieldErrorName -> {
                binding.name.error =
                    " Debe colocar un nombre / mas de 2 caracteres "
            }
            is MainActivityViewState.FieldErrorEmail -> {
                binding.email.error =
                    " Debe colocar un email, mayor a 5 caracteres  "
            }
            is MainActivityViewState.FieldErrorDni -> {
                binding.dni.error =
                    "El DNI debe tener 8 caracteres"
            }

            is MainActivityViewState.SuccessName -> {
                binding.name.error = null
            }
            is MainActivityViewState.SuccessEmail -> {
                binding.email.error = null
            }
            is MainActivityViewState.SuccessDni -> {
                binding.dni.error = null
            }

            is MainActivityViewState.FieldErrorBtn -> {
                binding.btnAddUser.isEnabled = false
            }
            is MainActivityViewState.SuccessBtn -> {
                binding.btnAddUser.isEnabled = true
            }
        }
    }

    private fun insertDataToDatabase() {
        val name = binding.name.text.toString()
        val email = binding.email.text.toString()
        val dni = binding.dni.text
        val lenguaje = binding.lenguajes.toString()



        if (inputCheck(name, email, dni, lenguaje)) {
            // Crea el objeto del usuario
            val user = User(Integer.parseInt(dni.toString()), name, email, lenguaje)

            // Agrega el usuario a la base de datos
            userViewModel.addUser(user)
            /*Toast.makeText(requireContext(), "Succesfully added!", Toast.LENGTH_LONG).show()

            // Navegamos a la lista de añadidos
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Complete all the fields!", Toast.LENGTH_LONG).show()*/
        }
    }

    private fun inputCheck(
        userName: String,
        userEmail: String,
        userDni: Editable,
        userLenguaje: String
    ): Boolean {
        return !(TextUtils.isEmpty(userName) && TextUtils.isEmpty(userEmail) && userDni.isEmpty() && TextUtils.isEmpty(
            userLenguaje
        ))
    }


}