package com.unal.lab_0.Controllers;
import com.unal.lab_0.Persistence.Model.Vivienda;
import com.unal.lab_0.Services.Interfaces.ViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vivienda")
public class ViviendaController {

    @Autowired
    ViviendaService viviendaService;

    @GetMapping("/all")
    public ModelAndView getAllViviendas(ModelAndView mv){
        mv.setViewName("viviendaTemplate");
        try {
            mv.getModel().put("viviendas", viviendaService.getAllViviendas());
            mv.getModel().put("viviendaToSave", new Vivienda());
        } catch (Exception e) {
            mv.getModel().put("error", "something was wrong, try again");
        }
        return mv;
    }
    @PostMapping("new")
    public ModelAndView newPersona(@ModelAttribute(name = "viviendaToSave") Vivienda viviendaToSave) {
        ModelAndView mv = new ModelAndView();
        try {
            viviendaService.create(viviendaToSave);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ModelAndView("redirect:/vivienda/all?error=Failed saving register");
        }
        return new ModelAndView("redirect:/vivienda/all?success=Register created");
    }

    @GetMapping("/find/{direccion}")
    public ModelAndView getByDireccion(ModelAndView mv,@PathVariable(name = "direccion") String viviendaTofind){
        mv.setViewName("viviendaTemplate.html");
        try {
            List<Vivienda> viviendas = new ArrayList<>();
            Vivienda vivienda = viviendaService.findByDireccion(viviendaTofind);
            if (vivienda != null) {
                viviendas.add(vivienda);
                mv.getModel().put("viviendas", viviendas);
            } else {
                mv.getModel().put("viviendas", null);
                mv.getModel().put("error", "register not found");
            }
        } catch (Exception e) {
            mv.getModel().put("viviendas", null);
            mv.getModel().put("error", "register not found");
            System.err.println(e.getMessage());
        }
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView editVivienda(@ModelAttribute(name = "personaToEdit") Vivienda viviendaToEdit,ModelAndView mv) {
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
            return new ModelAndView("redirect:/vivienda/all?error=Failed deleting");
        }
        return new ModelAndView("redirect:/vivienda/all?success=Register deleted");
    }
}
