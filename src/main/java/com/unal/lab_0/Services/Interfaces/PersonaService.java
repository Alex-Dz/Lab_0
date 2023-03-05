package com.unal.lab_0.Services.Interfaces;

import com.unal.lab_0.Persistence.Model.Persona;

import java.util.List;

public interface PersonaService {

    public Persona create(Persona personaToCreate) throws Exception;

    public Persona getByNombre(String nombre) throws Exception;

    public Persona getById(Integer id) throws Exception;

    public Persona edit(Persona personaToEdit) throws Exception;

    public Persona delete(Integer id) throws Exception;

    public List<Persona> getAllPersonas() throws Exception;

    public Boolean existById(Integer id) throws Exception;
}
