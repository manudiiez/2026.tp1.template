package com.bibliotech.service;

import com.bibliotech.model.*;
import com.bibliotech.repository.RecursoRepository;
import java.util.List;

public class RecursoServiceImpl implements RecursoService {
    private final RecursoRepository recursoRepository;

    public RecursoServiceImpl(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    @Override
    public void registrarLibroFisico(LibroFisico libro) {
        recursoRepository.guardar(libro);
    }

    @Override
    public void registrarEbook(Ebook ebook) {
        recursoRepository.guardar(ebook);
    }

    @Override
    public List<Recurso> buscarPorTitulo(String titulo) {
        throw new UnsupportedOperationException("Implementar en Issue #9");
    }

    @Override
    public List<Recurso> buscarPorAutor(String autor) {
        throw new UnsupportedOperationException("Implementar en Issue #9");
    }

    @Override
    public List<Recurso> buscarPorCategoria(CategoriaLibro categoria) {
        throw new UnsupportedOperationException("Implementar en Issue #9");
    }

    @Override
    public List<Recurso> listarTodos() {
        return recursoRepository.buscarTodos();
    }
}