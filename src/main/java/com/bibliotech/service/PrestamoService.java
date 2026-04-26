package com.bibliotech.service;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.model.Prestamo;
import java.util.List;

public interface PrestamoService {
    void realizarPrestamo(String isbn, int socioId) throws BibliotecaException;
    void registrarDevolucion(int prestamoId, int socioId) throws BibliotecaException;
    List<Prestamo> listarPrestamosActivos();
    List<Prestamo> listarPorSocio(int socioId);
}