package com.unal.lab_0.Services;

import com.unal.lab_0.Persistence.Model.Municipio;
import com.unal.lab_0.Persistence.MunicipioRepository;
import com.unal.lab_0.Services.Interfaces.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioServicelmpl implements MunicipioService {

    @Autowired
    MunicipioRepository municipioRepo;

    @Override
    public Municipio create(Municipio municipioToCreate) throws Exception {
        try {
            return municipioRepo.save(municipioToCreate);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Municipio getByNombre(String nombre) throws Exception {
        try {
            return municipioRepo.findByNombreContains(nombre);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Municipio getById(Integer id) throws Exception {
        try {
            return municipioRepo.findById(id).get();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Municipio edit(Municipio municipioToEdit) throws Exception {
        return create(municipioToEdit);
    }

    @Override
    public Municipio delete(Integer id) throws Exception {
        try {
            Municipio reg = municipioRepo.findById(id).get();
            municipioRepo.deleteById(id);
            return reg;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Municipio> getAllMunicipios() throws Exception {
        /*List<Municipio> municipios = new ArrayList<Municipio>();
        municipioRepo.findAll().forEach(municipios::add);
        return municipios;*/
        return municipioRepo.findAllByIdIsNotNullOrderByNombre();
    }

    @Override
    public boolean existByNombre(String nombre) throws Exception {
        try {
            return municipioRepo.existsByNombreIgnoreCase(nombre);
        } catch (Exception e) {
            throw e;
        }
    }
}
