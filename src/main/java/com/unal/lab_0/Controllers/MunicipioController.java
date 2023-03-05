package com.unal.lab_0.Controllers;

import com.unal.lab_0.Persistence.Model.Municipio;
import com.unal.lab_0.Services.Interfaces.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/municipio")
public class MunicipioController {
    @Autowired
    private MunicipioService municipioService;

    @GetMapping("/all")
    public ModelAndView getAllMunicipios(ModelAndView mv){
        mv.setViewName("municipioTemplate");
        try {
            mv.getModel().put("municipios", municipioService.getAllMunicipios());
            mv.getModel().put("municipioToSave", new Municipio());
        } catch (Exception e) {
            mv.getModel().put("error", "something was wrong, try again");
        }
        return mv;
    }
    @PostMapping("/new")
    public ModelAndView newMunicipio(@ModelAttribute(name = "municipioToSave") Municipio municipioToSave, ModelAndView mv) {
        mv.setViewName("municipioTemplate.html");
        try {
            municipioService.create(municipioToSave);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ModelAndView("redirect:/municipio/all?error=Failed saving register");
        }
        return new ModelAndView("redirect:/municipio/all?success=Register created");
    }

    @GetMapping("/find/{name}")
    public ModelAndView getByNombre(@PathVariable(name = "name") String municipioTofind, ModelAndView mv){
        mv.setViewName("municipioTemplate.html");
        try {
            List<Municipio> municipios = new ArrayList<>();
            Municipio municipio = municipioService.findByNombre(municipioTofind);
            if (municipio != null) {
                municipios.add(municipio);
                mv.getModel().put("municipios", municipios);
            } else {
                mv.getModel().put("municipios", null);
                mv.getModel().put("error", "register not found");
            }
        } catch (Exception e) {
            mv.getModel().put("municipios", null);
            mv.getModel().put("error", "register not found");
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
            return new ModelAndView("redirect:/municipio/all?error=Failed deleting");
        }
        return new ModelAndView("redirect:/municipio/all?success=Register deleted");
    }
}
