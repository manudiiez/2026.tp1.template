package com.bibliotech.model;

import java.time.LocalDate;
import java.util.Optional;

public record Prestamo(
        int id,
        int socioId,
        String isbn,
        LocalDate fechaInicio,
        LocalDate fechaDevolucionEsperada,
        Optional<LocalDate> fechaDevolucionReal
) {
    // Constructor de conveniencia para prestamos sin devolver
    public static Prestamo nuevo(int id, int socioId, String isbn, LocalDate inicio) {
        return new Prestamo(id, socioId, isbn, inicio, inicio.plusDays(14), Optional.empty());
    }

    public Prestamo conDevolucion(LocalDate fechaReal) {
        return new Prestamo(id, socioId, isbn, fechaInicio, fechaDevolucionEsperada, Optional.of(fechaReal));
    }
}