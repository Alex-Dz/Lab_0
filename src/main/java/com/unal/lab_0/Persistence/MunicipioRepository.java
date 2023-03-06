package com.unal.lab_0.Persistence;

import com.unal.lab_0.Persistence.Model.Municipio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Integer> {
    Municipio findByNombreContains(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);

    List<Municipio> findAllByIdIsNotNullOrderByNombre();
}
