package com.aptivist.basicscourse

interface IPersonaRepository {
    fun setPersona(persona: Persona)
    fun getPersona() :Persona
    fun cambiarNombre(nombre:String)
    fun cambiarApellido(apellido:String)
}