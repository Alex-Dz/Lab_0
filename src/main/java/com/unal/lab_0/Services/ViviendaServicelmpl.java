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
    public Vivienda create(Vivienda viviendaToCreate) throws Exception{
        try {
            return viviendaRepo.save(viviendaToCreate);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Vivienda findByDireccion(String direccion) throws Exception {
        return null;
    }

    @Override
    public Vivienda edit(Vivienda viviendaToEdit) throws Exception {
        return null;
    }

    @Override
    public Vivienda delete(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Vivienda> getAllViviendas() throws Exception {
        return null;
    }
}
