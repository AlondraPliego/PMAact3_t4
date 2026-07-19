package com.ProgramacionWebPMA.PMAact3_t4_CRUD.controllers;

import com.ProgramacionWebPMA.PMAact3_t4_CRUD.models.Libro;
import com.ProgramacionWebPMA.PMAact3_t4_CRUD.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroRestController {

    @Autowired
    private LibroService libroService;

    // GET /api/libros
    @GetMapping
    public List<Libro> listar() {
        return libroService.listarTodos();
    }

    // GET /api/libros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscar(@PathVariable Integer id) {
        Libro libro = libroService.buscar(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }

    // POST /api/libros
    @PostMapping
    public ResponseEntity<Libro> crear(@RequestBody Libro libro) {
        Libro guardado = libroService.guardar(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // PUT /api/libros/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Integer id, @RequestBody Libro libro) {
        Libro existente = libroService.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        libro.setId(id);
        Libro actualizado = libroService.guardar(libro);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE /api/libros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Libro existente = libroService.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}