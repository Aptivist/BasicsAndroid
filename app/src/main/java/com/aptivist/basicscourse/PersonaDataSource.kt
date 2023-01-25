package com.aptivist.basicscourse

class PersonaDataSource : IPersonaRepository{

    var myPerson : Persona = Persona("", "")

    override fun setPersona(persona: Persona) {
        myPerson = persona
    }

    override fun getPersona() :Persona {
        return myPerson
    }

    override fun cambiarNombre(nombre:String) {
        myPerson.nombre = nombre
    }

    override fun cambiarApellido(apellido:String) {
        myPerson.apellido = apellido
    }

}