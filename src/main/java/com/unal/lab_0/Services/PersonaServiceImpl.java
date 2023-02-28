package com.unal.lab_0.Services;

import com.unal.lab_0.Persistence.Model.Persona;
import com.unal.lab_0.Persistence.PersonaRepository;
import com.unal.lab_0.Services.Interfaces.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepo;

    @Override
    public Persona create(Persona personaToCreate) throws Exception {
        try {
            return personaRepo.save(personaToCreate);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Persona findByNombre(String nombre) throws Exception {
        try {
            return personaRepo.findByNombreContains(nombre);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Persona edit(Persona personaToEdit) throws Exception {
        return create(personaToEdit);
    }

    @Override
    public Persona delete(Integer id) throws Exception {
        try {
            Persona reg = personaRepo.findById(id).get();
            personaRepo.deleteById(id);
            return reg;
        } catch (Exception e) {
            throw e;
        }
    }
}