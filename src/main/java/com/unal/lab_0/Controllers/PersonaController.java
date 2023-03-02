package com.unal.lab_0.Controllers;

import com.unal.lab_0.Persistence.Model.Persona;
import com.unal.lab_0.Services.Interfaces.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/all")
    public ModelAndView getAllPersonas(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("personaTemplate.html");
        try {
            mv.getModel().put("personas",personaService.getAllPersonas());
        } catch (Exception e) {
            mv.getModel().put("personas",null);
        }
        return mv;
    }

    @PostMapping("new")
    public ModelAndView newPersona(@ModelAttribute(name = "personaToSave") Persona personaToSave) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("personaTemplate.html");
        try {
            mv.getModel().put("newPersona", personaService.create(personaToSave));
        } catch (Exception e) {
            mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
        }
        return mv;
    }

}
