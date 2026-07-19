package com.ProgramacionWebPMA.PMAact3_t4_CRUD.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProgramacionWebPMA.PMAact3_t4_CRUD.models.Libro;
import com.ProgramacionWebPMA.PMAact3_t4_CRUD.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> buscarPorNombre(String titulo) {
        return libroRepository.findByTituloContaining(titulo);
    }

    public Libro buscar(Integer id) {
        return libroRepository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        libroRepository.deleteById(id);
    }

}
