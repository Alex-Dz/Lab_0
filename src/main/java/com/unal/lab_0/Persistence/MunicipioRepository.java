package com.unal.lab_0.Persistence;

import com.unal.lab_0.Persistence.Model.Municipio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Integer> {
}
