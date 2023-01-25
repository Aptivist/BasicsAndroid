package com.aptivist.basicscourse

import android.util.Log

class APIPersonaDataSource : IPersonaRepository {

    override fun setPersona(persona: Persona) {
        Log.i("PersonaRepo", "SET")
    }

    override fun getPersona(): Persona {
        Log.i("PersonaRepo", "GET")
        return Persona("", "")
    }

    override fun cambiarNombre(nombre: String) {
        Log.i("PersonaRepo", "NOMBRE")
    }

    override fun cambiarApellido(apellido: String) {
        Log.i("PersonaRepo", "APELLIDO")
    }

}