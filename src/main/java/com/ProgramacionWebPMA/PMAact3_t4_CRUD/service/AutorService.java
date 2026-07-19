package com.ProgramacionWebPMA.PMAact3_t4_CRUD.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProgramacionWebPMA.PMAact3_t4_CRUD.models.Autor;
import com.ProgramacionWebPMA.PMAact3_t4_CRUD.repository.AutorRepository;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor buscar(Integer id) {
        return autorRepository.findById(id).orElse(null);
    }
}
