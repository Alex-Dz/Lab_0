package com.unal.lab_0.Controllers;

import com.unal.lab_0.Persistence.Model.Persona;
import com.unal.lab_0.Services.Interfaces.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.ui.ModelMap;
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
    public ModelAndView getAllPersonas(ModelAndView mv){
        //ModelAndView mv = new ModelAndView();
        mv.setViewName("personaTemplate");
        try {
            mv.getModel().put("personas",personaService.getAllPersonas());
            //model.addAttribute("personas",personaService.getAllPersonas());
        } catch (Exception e) {
            mv.getModel().put("personas",new ArrayList<Persona>());
            //model.addAttribute("personas",null);
        }
        //return "personaTemplate";
        return mv;
    }
    /*@PostMapping("new")
    public ModelAndView newPersona(@ModelAttribute(name = "personaToSave") Persona personaToSave) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("personaTemplate.html");
        try {
            mv.getModel().put("newPersona", personaService.create(personaToSave));
        } catch (Exception e) {
            mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
        }
        //return mv;
        return "personaTemplate";
    }*/

    @GetMapping("/{name}")
    public ModelAndView getByNombre(@PathVariable(name = "name") String personaTofind){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("personaTemplate.html");
        try {
            List<Persona> personas = new ArrayList<>();
            Persona persona = personaService.findByNombre(personaTofind);
            if (persona != null) {
                personas.add(persona);
                mv.getModel().put("personas", personas);
            }
            mv.getModel().put("personas", null);
        } catch (Exception e){
            mv.getModel().put("personas", null);
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @PostMapping("update")
    public ModelAndView editPersona(@ModelAttribute(name = "personaToEdit") Persona personaToEdit) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("personaTemplate.html");
        try {
            mv.getModel().put("editPersona", personaService.edit(personaToEdit));
        } catch (Exception e) {
            mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @PostMapping("new")
    public ModelAndView delPersona(@ModelAttribute(name = "personaToDelete") Integer idToDelete) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("personaTemplate.html");
        try {
            mv.getModel().put("delPersona", personaService.delete(idToDelete));
        } catch (Exception e) {
            mv.getModel().put("error", "Failed deleting register");
            System.err.println(e.getMessage());
        }
        return mv;
    }
}
