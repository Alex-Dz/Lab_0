package com.unal.lab_0.Services.Interfaces;

import com.unal.lab_0.Persistence.Model.Persona;
import com.unal.lab_0.Persistence.Model.Vivienda;

import java.util.List;
public interface ViviendaService {

    public Vivienda create(Vivienda viviendaToCreate) throws Exception;

    public Vivienda findByNombre(String nombre) throws Exception;

    public Vivienda edit(Vivienda viviendaToEdit) throws Exception;

    public Vivienda delete(Integer id) throws Exception;

    public List<Vivienda> getAllViviendas() throws Exception;
}