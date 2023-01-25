package com.aptivist.basicscourse

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainActivityComposeViewModel : ViewModel() {

    private val personaDataSource : IPersonaRepository = PersonaDataSource()

    private val name = mutableStateOf("")
    val pName : State<String>
    get() = name

    private val surname = mutableStateOf("")
    val pSurname : State<String>
        get() = surname

    private val _navigationAction = Channel<Actions>()
    val navigationAction = _navigationAction.consumeAsFlow()

    fun savePerson(){
        personaDataSource.cambiarNombre(name.value)
        personaDataSource.cambiarApellido(surname.value)
        _navigationAction.trySend(Actions.ShowError)

    }

    fun clearScreen(){
        name.value = ""
        surname.value = ""
        _navigationAction.trySend(Actions.Navigate("Id"))
    }

    fun getPerson(){
        val persona = personaDataSource.getPersona()
        name.value = persona.nombre
        surname.value = persona.apellido
        navigate()

    }

    fun setNewName(text:String){
        name.value = text
    }

    fun setNewSurname(text:String){
        surname.value = text
    }

    private fun navigate() {
       _navigationAction.trySend(Actions.ShowToast("I'm a message"))
    }

}

sealed class Actions() {
    object ShowError: Actions()
    data class ShowToast(val message: String): Actions()
    data class Navigate(val idView: String): Actions()
}