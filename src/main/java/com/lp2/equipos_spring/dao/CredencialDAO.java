/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lp2.equipos_spring.dao;

import com.lp2.equipos_spring.modelo.Credencial;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marcelo Esperguel
 */
public interface CredencialDAO extends CrudRepository<Credencial, Integer> {

    @Override
    public List<Credencial> findAll();
    
    public Credencial findByUsuarioAndPassword(String user, String pass);
}
















//public Credencial findByUsuarioAndPassword(String user,String pass);