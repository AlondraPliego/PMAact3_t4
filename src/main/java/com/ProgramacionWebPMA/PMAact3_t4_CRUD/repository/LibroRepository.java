package com.ProgramacionWebPMA.PMAact3_t4_CRUD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProgramacionWebPMA.PMAact3_t4_CRUD.models.Autor;
import com.ProgramacionWebPMA.PMAact3_t4_CRUD.models.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
List<Libro> findByTituloContaining(String titulo);
}
