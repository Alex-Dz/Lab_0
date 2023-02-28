package com.unal.lab_0.Services.Interfaces;

import com.unal.lab_0.Persistence.Model.Persona;

public interface PersonaService {

    public Persona create(Persona personaToCreate) throws Exception;

    public Persona findByNombre(String nombre) throws Exception;

    public Persona edit(Persona personaToEdit) throws Exception;

    public Persona delete(Integer id) throws Exception;
}
