package com.unal.lab_0.Services;
import com.unal.lab_0.Persistence.Model.Municipio;
import com.unal.lab_0.Persistence.MunicipioRepository;
import com.unal.lab_0.Services.Interfaces.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioServicelmpl implements MunicipioService {
    @Override
    public Municipio create(Municipio municipioToCreate) throws Exception {
        return null;
    }

    @Override
    public Municipio findByNombre(String nombre) throws Exception {
        return null;
    }

    @Override
    public Municipio edit(Municipio municipioToEdit) throws Exception {
        return null;
    }

    @Override
    public Municipio delete(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Municipio> getAllMunicipios() throws Exception {
        return null;
    }
}
