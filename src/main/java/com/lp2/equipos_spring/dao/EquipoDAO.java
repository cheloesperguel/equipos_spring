/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lp2.equipos_spring.dao;

import com.lp2.equipos_spring.modelo.Equipo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marcelo Esperguel
 */
public interface EquipoDAO extends CrudRepository<Equipo, Integer> {

    @Override
    public List<Equipo> findAll();

   // findBy+(atributo)
   public Equipo findById(int id);
    
    
    
}
