package com.ProgramacionWebPMA.PMAact3_t4_CRUD.controllers;

import com.ProgramacionWebPMA.PMAact3_t4_CRUD.service.LibroService;
import com.ProgramacionWebPMA.PMAact3_t4_CRUD.service.AutorService;
import com.ProgramacionWebPMA.PMAact3_t4_CRUD.models.Libro;
import com.ProgramacionWebPMA.PMAact3_t4_CRUD.models.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    // LIBROS ENDPOINTS

    
    @GetMapping("/")
    public String listar(Model model) {
        List<Libro> libros = libroService.listarTodos();
        model.addAttribute("libros", libros);
        if (libros.isEmpty()) {
            model.addAttribute("aviso", "Todavía no hay libros registrados. ¡Agrega el primero!");
        }
        return "biblioteca";
    }

  
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("autores", autorService.listarTodos());
        return "libroForm";
    }

    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Libro libro, RedirectAttributes redirectAttributes) {
        boolean sinTitulo = libro.getTitulo() == null || libro.getTitulo().isBlank();
        boolean sinAutor = libro.getAutor() == null || libro.getAutor().getId() == null;

        if (sinTitulo || sinAutor) {
            redirectAttributes.addFlashAttribute("error",
                    "Debes ingresar al menos el título y seleccionar un autor.");
            return libro.getId() == null ? "redirect:/nuevo" : "redirect:/editar/" + libro.getId();
        }

        boolean esNuevo = (libro.getId() == null);
        libroService.guardar(libro);
        redirectAttributes.addFlashAttribute("mensaje",
                esNuevo ? "Libro agregado correctamente." : "Libro actualizado correctamente.");
        return "redirect:/";
    }

    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Libro libro = libroService.buscar(id);
        if (libro == null) {
            redirectAttributes.addFlashAttribute("error", "No se encontró el libro solicitado.");
            return "redirect:/";
        }
        model.addAttribute("libro", libro);
        model.addAttribute("autores", autorService.listarTodos());
        return "libroForm";
    }

  
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Libro libro = libroService.buscar(id);
            libroService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Libro eliminado correctamente.");
        return "redirect:/";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String busqueda, Model model) {
        List<Libro> libros;
        if (busqueda == null || busqueda.isBlank()) {
            libros = libroService.listarTodos();
        } else {
            libros = libroService.buscarPorNombre(busqueda);
            if (libros.isEmpty()) {
                model.addAttribute("error", "No se encontraron libros con el título \"" + busqueda + "\".");
            }
        }
        model.addAttribute("libros", libros);
        return "biblioteca";
    }

    // AUTORES ENDPOINTS
    @GetMapping("/autores/nuevo")
    public String nuevoAutor(Model model) {
        model.addAttribute("autor", new Autor());
        return "autorForm";
    }

    @PostMapping("/autores/guardar")
    public String guardarAutor(@ModelAttribute Autor autor, RedirectAttributes redirectAttributes) {
        if (autor.getNombre() == null || autor.getNombre().isBlank()) {
            redirectAttributes.addFlashAttribute("error", "El nombre del autor es obligatorio.");
            return "redirect:/autores/nuevo";
        }
        autorService.guardar(autor);
        redirectAttributes.addFlashAttribute("mensaje", "Autor agregado correctamente.");
        return "redirect:/autores/nuevo";
    }

    @GetMapping("/autores")
    public String listarAutores(Model model) {
        List<Autor> autores = autorService.listarTodos();
        model.addAttribute("autores", autores);
        if (autores.isEmpty()) {
            model.addAttribute("aviso", "Todavía no hay autores registrados.");
        }
        return "autorLista";
    }
}
