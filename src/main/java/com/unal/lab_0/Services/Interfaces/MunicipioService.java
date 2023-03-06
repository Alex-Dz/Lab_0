package com.unal.lab_0.Services.Interfaces;

import com.unal.lab_0.Persistence.Model.Municipio;

import java.util.List;

public interface MunicipioService {

    public Municipio create(Municipio municipioToCreate) throws Exception;

    //
    public Municipio getByNombre(String nombre) throws Exception;

    public Municipio getById(Integer id) throws Exception;

    public Municipio edit(Municipio municipioToEdit) throws Exception;

    public Municipio delete(Integer id) throws Exception;

    public List<Municipio> getAllMunicipios() throws Exception;

    public boolean existByNombre(String nombre) throws Exception;

}
