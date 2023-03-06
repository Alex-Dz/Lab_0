package com.unal.lab_0.Controllers;

import com.unal.lab_0.Persistence.Model.Municipio;
import com.unal.lab_0.Services.Interfaces.MunicipioService;
import com.unal.lab_0.Services.Interfaces.PersonaService;
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
    private PersonaService personaService;
    @Autowired
    private MunicipioService municipioService;

    @GetMapping("/all")
    public ModelAndView getAllMunicipios(ModelAndView mv, Municipio municipioToSave, String successMsg, String errorMsg) {
        mv.setViewName("municipioTemplate");
        try {
            mv.getModel().put("personas", personaService.getAllPersonas());
            mv.getModel().put("municipios", municipioService.getAllMunicipios());
            if (municipioToSave == null)
                mv.getModel().put("municipioToSave", new Municipio());
            else
                mv.getModel().put("municipioToSave", municipioToSave);
            mv.getModel().put("success", successMsg);
            mv.getModel().put("error", errorMsg);
        } catch (Exception e) {
            mv.getModel().put("error", "something was wrong, try again");
        }
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView newMunicipio(@ModelAttribute(name = "municipioToSave") Municipio municipioToSave, ModelAndView mv) {
        try {
            if (!municipioService.existByNombre(municipioToSave.getNombre()))
                municipioService.create(municipioToSave);
            else
                return getAllMunicipios(mv, municipioToSave, null, "register already exists");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return getAllMunicipios(mv, municipioToSave, null, "Failed saving register");
        }
        return getAllMunicipios(mv, municipioToSave, "Register created", null);
    }

    @GetMapping("/find/{name}")
    public ModelAndView getByNombre(@PathVariable(name = "name") String municipioTofind, ModelAndView mv) {
        mv.setViewName("municipioTemplate.html");
        try {
            List<Municipio> municipios = new ArrayList<>();
            Municipio municipio = municipioService.getByNombre(municipioTofind);
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

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(ModelAndView mv, @PathVariable("id") Integer id) {
        Municipio municipioToSave = null;
        try {
            municipioToSave = municipioService.getById(id);
            mv.getModel().put("edit", true);
        } catch (Exception e) {
            return getAllMunicipios(mv, municipioToSave, null, "something was wrong, try again");
        }
        return getAllMunicipios(mv, municipioToSave, null, null);
    }

    @PostMapping("/update")
    public ModelAndView editMunicipio(@ModelAttribute(name = "municipioToSave") Municipio municipioToEdit, ModelAndView mv) {
        try {
            municipioService.edit(municipioToEdit);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return getAllMunicipios(mv, municipioToEdit, null, "failed updating the register");
        }

        return getAllMunicipios(mv, municipioToEdit, "register updated", null);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delMunicipio(@PathVariable(name = "id") Integer idToDelete, ModelAndView mv) {
        //mv.setViewName("municipioTemplate.html");
        try {
            municipioService.delete(idToDelete);
        } catch (Exception e) {
            //mv.getModel().put("error", "Failed deleting register");
            System.err.println(e.getMessage());
            //return new ModelAndView("redirect:/municipio/all?error=Failed deleting");
            return getAllMunicipios(mv, null, null, "Failed deleting");
        }
        //return new ModelAndView("redirect:/municipio/all?success=Register deleted");
        return getAllMunicipios(mv, null, "Register deleted", null);
    }
}
