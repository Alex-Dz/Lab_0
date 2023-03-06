package com.unal.lab_0.Services;

import com.unal.lab_0.Persistence.Model.Vivienda;
import com.unal.lab_0.Persistence.ViviendaRepository;
import com.unal.lab_0.Services.Interfaces.ViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViviendaServicelmpl implements ViviendaService {

    @Autowired
    ViviendaRepository viviendaRepo;

    @Override
    public Vivienda create(Vivienda viviendaToCreate) throws Exception {
        try {
            return viviendaRepo.save(viviendaToCreate);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Vivienda getByDireccion(String direccion) throws Exception {
        try {
            return viviendaRepo.findByDireccionContains(direccion);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Vivienda getById(Integer id) throws Exception {
        try {
            return viviendaRepo.findById(id).get();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Vivienda edit(Vivienda viviendaToEdit) throws Exception {
        return create(viviendaToEdit);
    }

    @Override
    public Vivienda delete(Integer id) throws Exception {
        try {
            Vivienda reg = viviendaRepo.findById(id).get();
            viviendaRepo.deleteById(id);
            return reg;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Vivienda> getAllViviendas() throws Exception {
        /*List<Vivienda> viviendas = new ArrayList<Vivienda>();
        viviendaRepo.findAll().forEach(viviendas::add);
        return viviendas;*/
        return viviendaRepo.findAllByIdIsNotNullOrderByMunicipio();
    }

    @Override
    public Boolean existByDireccion(String direccion) throws Exception {
        try {
            return viviendaRepo.existsByDireccionContains(direccion);
        } catch (Exception e) {
            throw e;
        }
    }
}
