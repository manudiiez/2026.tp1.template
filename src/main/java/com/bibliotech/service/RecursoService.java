package com.bibliotech.service;

import com.bibliotech.model.CategoriaLibro;
import com.bibliotech.model.Ebook;
import com.bibliotech.model.LibroFisico;
import com.bibliotech.model.Recurso;
import java.util.List;

public interface RecursoService {
    void registrarLibroFisico(LibroFisico libro);
    void registrarEbook(Ebook ebook);
    List<Recurso> buscarPorTitulo(String titulo);
    List<Recurso> buscarPorAutor(String autor);
    List<Recurso> buscarPorCategoria(CategoriaLibro categoria);
    List<Recurso> listarTodos();
}