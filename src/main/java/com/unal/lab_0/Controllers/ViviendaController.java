package com.unal.lab_0.Controllers;
import com.unal.lab_0.Persistence.Model.Vivienda;
import com.unal.lab_0.Services.Interfaces.ViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/vivienda")
public class ViviendaController {

    @Autowired
    ViviendaService viviendaService;

    @GetMapping("/all")
    public ModelAndView getAllViviendas(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("viviendaTemplate.html");
        try {
            mv.getModel().put("viviendas",viviendaService.getAllViviendas());
        } catch (Exception e) {
            mv.getModel().put("viviendas",null);
        }
        return mv;
    }
    @PostMapping("new")
    public ModelAndView newPersona(@ModelAttribute(name = "viviendaToSave") Vivienda viviendaToSave) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("viviendaTemplate.html");
        try {
            mv.getModel().put("newVivienda", viviendaService.create(viviendaToSave));
        } catch (Exception e) {
            mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @GetMapping("one")
    public ModelAndView getByDireccion(@ModelAttribute(name = "personaToFind") String viviendaTofind){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("viviendaTemplate.html");
        try {
            mv.getModel().put("dirVivienda", viviendaService.findByDireccion(viviendaTofind));
        } catch (Exception e){
            mv.getModel().put("error", "Vivienda not found");
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @PostMapping("update")
    public ModelAndView editVivienda(@ModelAttribute(name = "personaToEdit") Vivienda viviendaToEdit) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("viviendaTemplate.html");
        try {
            mv.getModel().put("editVivienda", viviendaService.edit(viviendaToEdit));
        } catch (Exception e) {
            mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
        }
        return mv;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delVivienda(@PathVariable(name = "viviendaToDelete") Integer idToDelete,ModelAndView mv) {
        //mv.setViewName("personaTemplate.html");
        try {
            viviendaService.delete(idToDelete);
        } catch (Exception e) {
            //mv.getModel().put("error", "Failed deleting register");
            System.err.println(e.getMessage());
            return new ModelAndView("redirect:/vivienda/all?error=failed deleting");
        }
        return new ModelAndView("redirect:/vivienda/all?success=register deleted");
    }
}
