package com.unal.lab_0.Controllers;

import com.unal.lab_0.Persistence.Model.Persona;
import com.unal.lab_0.Services.Interfaces.MunicipioService;
import com.unal.lab_0.Services.Interfaces.PersonaService;
import com.unal.lab_0.Services.Interfaces.ViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;
    @Autowired
    private MunicipioService municipioService;
    @Autowired
    private ViviendaService viviendaService;

    @GetMapping("/all")
    public ModelAndView getAllPersonas(ModelAndView mv, Persona personaToSave, String successMsg, String errorMsg) {
        mv.setViewName("personaTemplate");
        try {
            mv.getModel().put("personas", personaService.getAllPersonas());
            //mv.getModel().put("personas", personaService.getAllPersonasDto());
            mv.getModel().put("municipios", municipioService.getAllMunicipios());
            mv.getModel().put("viviendas", viviendaService.getAllViviendas());
            if (personaToSave == null)
                mv.getModel().put("personaToSave", new Persona());
            else
                mv.getModel().put("personaToSave", personaToSave);
            mv.getModel().put("success", successMsg);
            mv.getModel().put("error", errorMsg);
        } catch (Exception e) {
            mv.getModel().put("error", "something was wrong, try again");
        }
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView newPersona(ModelAndView mv, @ModelAttribute(name = "personaToSave") Persona personaToSave) {
        //mv.setViewName("personaTemplate");
        try {
            if (!personaService.existById(personaToSave.getId()))
                personaService.create(personaToSave);
            else
                return getAllPersonas(mv, personaToSave, null, "register already exists");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return getAllPersonas(mv, null, null, "failed saving register");
        }
        return getAllPersonas(mv, null, "register created", null);
    }

    @GetMapping("/{id}")
    public ModelAndView getPersonaById(ModelAndView mv, @PathVariable(name = "id") Integer personaTofindId) {
        mv.setViewName("personaTemplate");
        try {
            List<Persona> personas = new ArrayList<>();
            Persona persona = personaService.getById(personaTofindId);
            if (persona != null) {
                personas.add(persona);
                mv.getModel().put("personas", personas);
            } else {
                mv.getModel().put("personas", null);
                mv.getModel().put("error", "register not found");
            }
        } catch (Exception e) {
            mv.getModel().put("personas", null);
            mv.getModel().put("error", "register not found");
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @GetMapping("/find/{name}")
    public ModelAndView getPersonaByName(ModelAndView mv, @PathVariable(name = "name") String personaTofind) {
        mv.setViewName("personaTemplate");
        try {
            List<Persona> personas = new ArrayList<>();
            Persona persona = personaService.getByNombre(personaTofind);
            if (persona != null) {
                personas.add(persona);
                mv.getModel().put("personas", personas);
            } else {
                mv.getModel().put("personas", null);
                mv.getModel().put("error", "register not found");
            }
        } catch (Exception e) {
            mv.getModel().put("personas", null);
            mv.getModel().put("error", "register not found");
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(ModelAndView mv, @PathVariable("id") Integer id) {
        //mv.setViewName("personaTemplate");
        Persona personaToSave = null;
        try {
            //mv.getModel().put("personas", personaService.getAllPersonas());
            //mv.getModel().put("personaToSave", personaService.getById(id));
            //personaToSave = new PersonaDto(personaService.getById(id));
            personaToSave = personaService.getById(id);
            mv.getModel().put("edit", true);
        } catch (Exception e) {
            //mv.getModel().put("error", "something was wrong, try again");
            return getAllPersonas(mv, personaToSave, null, "something was wrong, try again");
        }
        return getAllPersonas(mv, personaToSave, null, null);
    }

    @PostMapping("/update")
    public ModelAndView editPersona(@ModelAttribute(name = "personaToSave") Persona personaToSave, ModelAndView mv) {
        try {
            personaService.edit(personaToSave);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return getAllPersonas(mv, personaToSave, null, "failed updating the register");
        }

        return getAllPersonas(mv, personaToSave, "register updated", null);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delPersona(@PathVariable(name = "id") Integer idToDelete, ModelAndView mv) {
        try {
            personaService.delete(idToDelete);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            //return new ModelAndView("redirect:/persona/all?error=Failed deleting");
            return getAllPersonas(mv, null, null, "Failed deleting");

        }
        //return new ModelAndView("redirect:/persona/all?success=Register deleted");
        return getAllPersonas(mv, null, "Register deleted", null);
    }
}
