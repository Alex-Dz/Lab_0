package com.unal.lab_0.Persistence;

import com.unal.lab_0.Persistence.Model.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer> {
}
