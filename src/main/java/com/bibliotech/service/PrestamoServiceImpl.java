package com.bibliotech.service;

import com.bibliotech.exception.*;
import com.bibliotech.model.*;
import com.bibliotech.repository.*;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PrestamoServiceImpl implements PrestamoService {
    private final RecursoRepository recursoRepository;
    private final SocioRepository socioRepository;
    private final PrestamoRepository prestamoRepository;
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public PrestamoServiceImpl(RecursoRepository recursoRepository,
                               SocioRepository socioRepository,
                               PrestamoRepository prestamoRepository) {
        this.recursoRepository = recursoRepository;
        this.socioRepository = socioRepository;
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public void realizarPrestamo(String isbn, int socioId) throws BibliotecaException {
        // 1. Verificar que el libro exista y este disponible
        Recurso recurso = recursoRepository.buscarPorId(isbn)
                .orElseThrow(() -> new LibroNoDisponibleException(isbn));

        if (recurso instanceof LibroFisico libro && !libro.disponible()) {
            throw new LibroNoDisponibleException(isbn);
        }

        // 2. Verificar que el socio exista
        Socio socio = socioRepository.buscarPorId(socioId)
                .orElseThrow(() -> new SocioNoEncontradoException(socioId));

        // 3. Verificar que el socio pueda tomar mas prestamos
        if (!socio.puedeTomarPrestamo()) {
            throw new LimitePrestamoAlcanzadoException(socioId);
        }

        // 4. Registrar el prestamo
        Prestamo prestamo = Prestamo.nuevo(idCounter.getAndIncrement(), socioId, isbn, LocalDate.now());
        prestamoRepository.guardar(prestamo);
        socio.agregarPrestamo(isbn);
    }

    @Override
    public void registrarDevolucion(int prestamoId, int socioId) throws BibliotecaException {
        throw new UnsupportedOperationException("Implementar en Issue #12");
    }

    @Override
    public List<Prestamo> listarPrestamosActivos() {
        return prestamoRepository.buscarActivos();
    }

    @Override
    public List<Prestamo> listarPorSocio(int socioId) {
        return prestamoRepository.buscarPorSocio(socioId);
    }
}