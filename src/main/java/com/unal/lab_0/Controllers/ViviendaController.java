package com.unal.lab_0.Controllers;

import com.unal.lab_0.Persistence.Model.Vivienda;
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
@RequestMapping("/vivienda")
public class ViviendaController {

    @Autowired
    ViviendaService viviendaService;
    @Autowired
    PersonaService personaService;
    @Autowired
    MunicipioService municipioService;

    @GetMapping("/all")
    public ModelAndView getAllViviendas(ModelAndView mv, Vivienda viviendaToSave, String successMsg, String errorMsg) {
        mv.setViewName("viviendaTemplate");
        try {
            mv.getModel().put("viviendas", viviendaService.getAllViviendas());
            mv.getModel().put("municipios", municipioService.getAllMunicipios());
            if (viviendaToSave == null)
                mv.getModel().put("viviendaToSave", new Vivienda());
            else
                mv.getModel().put("viviendaToSave", viviendaToSave);
            mv.getModel().put("success", successMsg);
            mv.getModel().put("error", errorMsg);
        } catch (Exception e) {
            mv.getModel().put("error", "something was wrong, try again");
        }
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView newPersona(ModelAndView mv, @ModelAttribute(name = "viviendaToSave") Vivienda viviendaToSave) {
        //ModelAndView mv = new ModelAndView();
        try {
            if (!viviendaService.existByDireccion(viviendaToSave.getDireccion()))
                viviendaService.create(viviendaToSave);
            else
                return getAllViviendas(mv, null, null, "register already exists");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            //return new ModelAndView("redirect:/vivienda/all?error=Failed saving register");
            return getAllViviendas(mv, null, null, "Failed saving register");
        }
        //return new ModelAndView("redirect:/vivienda/all?success=Register created");
        return getAllViviendas(mv, null, "Register created", null);
    }

    @GetMapping("/find/{direccion}")
    public ModelAndView getByDireccion(ModelAndView mv, @PathVariable(name = "direccion") String viviendaTofind) {
        mv.setViewName("viviendaTemplate.html");
        try {
            List<Vivienda> viviendas = new ArrayList<>();
            Vivienda vivienda = viviendaService.getByDireccion(viviendaTofind);
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

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(ModelAndView mv, @PathVariable("id") Integer id) {
        Vivienda viviendaToSave = null;
        try {
            viviendaToSave = viviendaService.getById(id);
            mv.getModel().put("edit", true);
        } catch (Exception e) {
            return getAllViviendas(mv, viviendaToSave, null, "something was wrong, try again");
        }
        return getAllViviendas(mv, viviendaToSave, null, null);
    }

    @PostMapping("/update")
    public ModelAndView editVivienda(@ModelAttribute(name = "personaToEdit") Vivienda viviendaToEdit, ModelAndView mv) {
        //mv.setViewName("viviendaTemplate.html");
        try {
            viviendaService.edit(viviendaToEdit);
        } catch (Exception e) {
            //mv.getModel().put("error", "Failed saving register");
            System.err.println(e.getMessage());
            return getAllViviendas(mv, viviendaToEdit, null, "failed updating the register");
        }
        return getAllViviendas(mv, viviendaToEdit, "register updated", null);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delVivienda(@PathVariable(name = "id") Integer idToDelete, ModelAndView mv) {
        //mv.setViewName("personaTemplate.html");
        try {
            viviendaService.delete(idToDelete);
        } catch (Exception e) {
            //mv.getModel().put("error", "Failed deleting register");
            System.err.println(e.getMessage());
            //return new ModelAndView("redirect:/vivienda/all?error=Failed deleting");
            return getAllViviendas(mv, null, null, "Failed deleting");
        }
        //return new ModelAndView("redirect:/vivienda/all?success=Register deleted");
        return getAllViviendas(mv, null, "Register deleted", null);
    }
}
