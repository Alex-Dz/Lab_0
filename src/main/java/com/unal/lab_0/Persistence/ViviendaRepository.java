package com.unal.lab_0.Persistence;

import com.unal.lab_0.Persistence.Model.Vivienda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViviendaRepository extends CrudRepository<Vivienda, Integer> {
}
