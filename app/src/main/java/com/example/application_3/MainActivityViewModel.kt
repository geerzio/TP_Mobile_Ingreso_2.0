package com.example.application_3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel : ViewModel() {

    private val _viewState = MutableLiveData<MainActivityViewState>()
    val viewState: LiveData<MainActivityViewState> get() = _viewState


    fun validateName(name:String){
        if (name.isEmpty() && name.length<2){
            _viewState.value = MainActivityViewState.FieldErrorName
            _viewState.value = MainActivityViewState.FieldErrorBtn

        }else{
            _viewState.value = MainActivityViewState.SuccessName
            _viewState.value = MainActivityViewState.SuccessBtn

        }
    }
    fun validateEmail(email:String){
        if (email.isEmpty() && email.length<5){

            _viewState.value = MainActivityViewState.FieldErrorEmail
            _viewState.value = MainActivityViewState.FieldErrorBtn
        }else{
            _viewState.value = MainActivityViewState.SuccessEmail
            _viewState.value = MainActivityViewState.SuccessBtn
        }
    }
    fun validateDni(dni:String){
        if (dni.isEmpty() && dni.length == 8){

            _viewState.value = MainActivityViewState.SuccessDni
            _viewState.value = MainActivityViewState.SuccessBtn
        }else{
            _viewState.value = MainActivityViewState.FieldErrorDni
            _viewState.value = MainActivityViewState.FieldErrorBtn
        }
    }


}


sealed class MainActivityViewState(){
    object FieldErrorName : MainActivityViewState()
    object SuccessName : MainActivityViewState()
    object FieldErrorEmail : MainActivityViewState()
    object SuccessEmail : MainActivityViewState()
    object FieldErrorDni : MainActivityViewState()
    object SuccessDni : MainActivityViewState()
    object SuccessBtn : MainActivityViewState()
    object FieldErrorBtn : MainActivityViewState()









}