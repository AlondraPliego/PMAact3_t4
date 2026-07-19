package com.ProgramacionWebPMA.PMAact3_t4_CRUD.controllers;

import com.ProgramacionWebPMA.PMAact3_t4_CRUD.models.Autor;
import com.ProgramacionWebPMA.PMAact3_t4_CRUD.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorRestController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listar() {
        return autorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscar(@PathVariable Integer id) {
        Autor autor = autorService.buscar(id);
        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<Autor> crear(@RequestBody Autor autor) {
        Autor guardado = autorService.guardar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }
}