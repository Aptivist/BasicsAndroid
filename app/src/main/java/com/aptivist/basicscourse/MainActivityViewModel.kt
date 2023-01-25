package com.aptivist.basicscourse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val personaDataSource : IPersonaRepository = PersonaDataSource()

    private val name = MutableLiveData("")
    val pName : LiveData<String>
    get() = name

    private val surname = MutableLiveData("")
    val pSurname : LiveData<String>
        get() = surname

    fun savePerson(){
        name.value?.let {
            personaDataSource.cambiarNombre(it)
        }
        surname.value?.let {
            personaDataSource.cambiarApellido(it)
        }
    }

    fun clearScreen(){
        name.value = ""
        surname.value = ""
    }

    fun getPerson(){
        val persona = personaDataSource.getPersona()
        name.value = persona.nombre
        surname.value = persona.apellido
    }

    fun setNewName(text:String){
        if(text != name.value) {
            name.value = text
        }
    }

    fun setNewSurname(text:String){
        if(text != surname.value) {
            surname.value = text
        }
    }

}