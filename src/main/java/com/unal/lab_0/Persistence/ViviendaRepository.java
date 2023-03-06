package com.unal.lab_0.Persistence;

import com.unal.lab_0.Persistence.Model.Vivienda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViviendaRepository extends CrudRepository<Vivienda, Integer> {
    Vivienda findByDireccionContains(String direccion);

    List<Vivienda> findAllByIdIsNotNullOrderByMunicipio();

    boolean existsByDireccionContains(String direccion);
}
