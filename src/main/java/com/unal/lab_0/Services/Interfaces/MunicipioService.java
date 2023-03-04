package com.unal.lab_0.Services.Interfaces;

import com.unal.lab_0.Persistence.Model.Municipio;

import java.util.List;
public interface MunicipioService {

    public Municipio create(Municipio municipioToCreate) throws Exception;
//
    public Municipio findByNombre(String nombre) throws Exception;

    public Municipio edit(Municipio municipioToEdit) throws Exception;

    public Municipio delete(Integer id) throws Exception;

    public List<Municipio> getAllMunicipios() throws Exception;

}
