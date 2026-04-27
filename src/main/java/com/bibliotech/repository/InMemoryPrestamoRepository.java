package com.bibliotech.repository;

import com.bibliotech.model.Prestamo;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryPrestamoRepository implements PrestamoRepository {
    private final Map<Integer, Prestamo> almacen = new HashMap<>();

    @Override
    public void guardar(Prestamo prestamo) {
        almacen.put(prestamo.id(), prestamo);
    }

    @Override
    public Optional<Prestamo> buscarPorId(Integer id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public List<Prestamo> buscarTodos() {
        return new ArrayList<>(almacen.values());
    }

    @Override
    public void eliminar(Integer id) {
        almacen.remove(id);
    }

    @Override
    public List<Prestamo> buscarPorSocio(int socioId) {
        return almacen.values().stream()
                .filter(p -> p.socioId() == socioId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Prestamo> buscarActivos() {
        return almacen.values().stream()
                .filter(p -> p.fechaDevolucionReal().isEmpty())
                .collect(Collectors.toList());
    }
}