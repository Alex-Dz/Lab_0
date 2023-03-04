package com.unal.lab_0.Controllers;

import com.unal.lab_0.Persistence.Model.Persona;
import com.unal.lab_0.Services.Interfaces.PersonaService;
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

    @GetMapping("/all")
    public ModelAndView getAllPersonas(ModelAndView mv) {
        mv.setViewName("personaTemplate");
        try {
            mv.getModel().put("personas", personaService.getAllPersonas());
            mv.getModel().put("personaToSave", new Persona());
        } catch (Exception e) {
            mv.getModel().put("error", "something was wrong, try again");
        }
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView newPersona(ModelAndView mv, @ModelAttribute(name = "personaToSave") Persona personaToSave) {
        mv.setViewName("personaTemplate");
        try {
            personaService.create(personaToSave);
            //mv.getModel().put("success", "register created");
        } catch (Exception e) {
            //mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
            return new ModelAndView("redirect:/persona/all?error=Failed saving register");
        }
        return new ModelAndView("redirect:/persona/all?success=register created");
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

    @PostMapping("/update")
    public ModelAndView editPersona(@ModelAttribute(name = "personaToEdit") Persona personaToEdit,ModelAndView mv) {
        mv.setViewName("personaTemplate.html");
        try {
            mv.getModel().put("editPersona", personaService.edit(personaToEdit));
        } catch (Exception e) {
            mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delPersona(@PathVariable (name = "id") Integer idToDelete,ModelAndView mv) {
        //mv.setViewName("personaTemplate.html");
        try {
            personaService.delete(idToDelete);
        } catch (Exception e) {
            //mv.getModel().put("error", "Failed deleting register");
            System.err.println(e.getMessage());
            return new ModelAndView("redirect:/persona/all?error=failed deleting");
        }
        return new ModelAndView("redirect:/persona/all?success=register deleted");
    }
}
