package com.ProgramacionWebPMA.PMAact3_t4_CRUD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProgramacionWebPMA.PMAact3_t4_CRUD.models.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer>{
    List<Autor> findByNombre(String nombre);

}
