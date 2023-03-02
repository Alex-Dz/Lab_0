package com.unal.lab_0.Persistence;

import com.unal.lab_0.Persistence.Model.Municipio;
import com.unal.lab_0.Persistence.Model.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Integer> {
    Municipio findByNombreContains(String nombre);

    List<Municipio> findAllBy();
}
