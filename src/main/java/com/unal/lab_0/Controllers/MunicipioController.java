package com.unal.lab_0.Controllers;

import com.unal.lab_0.Persistence.Model.Municipio;
import com.unal.lab_0.Services.Interfaces.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public class MunicipioController {
    @Autowired
    private MunicipioService municipioService;

    @GetMapping("/all")
    public ModelAndView getAllMunicipios(ModelAndView mv){
        mv.setViewName("municipioTemplate");
        try {
            mv.getModel().put("municipios",municipioService.getAllMunicipios());
        } catch (Exception e) {
            mv.getModel().put("municipios",new ArrayList<Municipio>());
        }
        return mv;
    }
    @PostMapping("/new")
    public ModelAndView newMunicipio(@ModelAttribute(name = "municipioToSave") Municipio municipioToSave, ModelAndView mv) {
        mv.setViewName("municipioTemplate.html");
        try {
            mv.getModel().put("newMunicipio", municipioService.create(municipioToSave));
        } catch (Exception e) {
            mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @GetMapping("/{name}")
    public ModelAndView getByNombre(@PathVariable(name = "name") String municipioTofind, ModelAndView mv){
        mv.setViewName("municipioTemplate.html");
        try {
            List<Municipio> municipios = new ArrayList<>();
            Municipio municipio = municipioService.findByNombre(municipioTofind);
            if (municipio != null) {
                municipios.add(municipio);
                mv.getModel().put("municipio", municipios);
            }
            mv.getModel().put("municipio", null);
        } catch (Exception e){
            mv.getModel().put("municipio", null);
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView editMunicipio(@ModelAttribute(name = "personaToEdit") Municipio municipioToEdit,ModelAndView mv) {
        mv.setViewName("municipioTemplate.html");
        try {
            mv.getModel().put("editMunicipio", municipioService.edit(municipioToEdit));
        } catch (Exception e) {
            mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delMunicipio(@PathVariable (name = "id") Integer idToDelete,ModelAndView mv) {
        //mv.setViewName("municipioTemplate.html");
        try {
            municipioService.delete(idToDelete);
        } catch (Exception e) {
            //mv.getModel().put("error", "Failed deleting register");
            System.err.println(e.getMessage());
            return new ModelAndView("redirect:/municipio/all?error=failed deleting");
        }
        return new ModelAndView("redirect:/municipio/all?success=register deleted");
    }
}
