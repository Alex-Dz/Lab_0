package com.unal.lab_0.Persistence;

import com.unal.lab_0.Persistence.Model.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer> {
    Persona findByNombreContains(String nombre);
}
