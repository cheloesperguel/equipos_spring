/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lp2.equipos_spring.controlador;

import com.lp2.equipos_spring.dao.CredencialDAO;
import com.lp2.equipos_spring.modelo.Credencial;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Marcelo Esperguel
 */
@Controller
public class CredencialController {
    
    @Autowired
    CredencialDAO cDao;
    
    @GetMapping("/login")
    public String mostrarFormLogin(Model model){
        
        model.addAttribute("credencial", new Credencial());
        
        return "login";
    }
    
    @PostMapping("login")
    public String login(
            Model model,  
            @ModelAttribute Credencial c,
            HttpServletRequest request
    ){
        
        Credencial usuarioBD = cDao.findByUsuarioAndPassword(c.getUsuario(),c.getPassword());
        
        if(usuarioBD!=null){
            request.getSession().setAttribute("usuarioLogueado", usuarioBD);
            return "index";
        }else{
            model.addAttribute("credencial", new Credencial());
            model.addAttribute("error", true);
            return "login";
        } 
        
    }
    
    @PostMapping("logout")
    public String logout(HttpServletRequest request){
        
        request.getSession().invalidate();
        
        return "redirect:/";
    }
    
    
    
}

