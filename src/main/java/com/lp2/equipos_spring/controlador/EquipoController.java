/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lp2.equipos_spring.controlador;

import com.lp2.equipos_spring.dao.EquipoDAO;
import com.lp2.equipos_spring.modelo.Credencial;
import com.lp2.equipos_spring.modelo.Equipo;
import com.lp2.equipos_spring.modelo.Jugador;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Marcelo Esperguel
 */
@Controller
public class EquipoController {
    
    @Autowired
    private EquipoDAO eDao;
    
    @RequestMapping("/url")
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }
   
    @GetMapping("/equipos")
    public String muestraEquipos(Model model){
        
        
        List<Equipo> lista = eDao.findAll();
           

        model.addAttribute("listaEquipos", lista);
        
        return "equipos";
    }
       
    @GetMapping("/equipos/crear")
    public String muestraForm(
            Model model, 
            HttpServletRequest request
    ){
        
            Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");
            
            if(c==null){
                
                return "redirect:/login";
            }else{
               model.addAttribute("nuevoEquipo", new Equipo());

                return "CrearEquipoForm"; 
            
            }
    
        
    }

    @PostMapping("/equipos/crear")
    public String crearEquipo(@ModelAttribute Equipo e){
          
        eDao.save(e);
        
        return "redirect:/equipos";
    }

    
    @GetMapping("/equipos/{idEquipo}/ver")
    public String verEquipo(
        @PathVariable(name="idEquipo") Integer id, 
        Model model
    ){
        
        Equipo e = eDao.findById(id.intValue());
        
        model.addAttribute("equipo", e);
        
        return "verEquipo";
    }
    
    @GetMapping("/equipos/{idEquipo}/agregarJugador")
    public String agregarJugadorForm(
        Model model,
        @PathVariable(name="idEquipo") Integer id
    ){
        
        Equipo e = eDao.findById(id.intValue());
        
        model.addAttribute("equipo", e);
        model.addAttribute("nuevoJugador", new Jugador());
        
        return "crearJugadorForm";
    }
    
    @PostMapping("/equipos/{idEquipo}/agregarJugador")
    public String agregarJugador(
       @PathVariable(name="idEquipo") Integer idEquipo,
       @ModelAttribute Jugador j
    ){
        
        Equipo e = eDao.findById(idEquipo.intValue());
        
        e.getJugadorList().add(j);
        
        eDao.save(e);
        
        return "redirect:/equipos/"+idEquipo.toString()+"/ver";
    }
    
}













/*
 @GetMapping("/equipos/crear")
    public String muestraForm(Model model, HttpServletRequest request){
        
        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");
       
        if(c!=null){
            model.addAttribute("nuevoEquipo", new Equipo());

            return "CrearEquipoForm";
        }else{
            return "redirect:/login";
        }
    }

*/