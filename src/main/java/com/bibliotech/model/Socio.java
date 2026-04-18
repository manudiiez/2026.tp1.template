package com.bibliotech.model;

import java.util.ArrayList;
import java.util.List;

public class Socio {
    private final int id;
    private final String nombre;
    private final String dni;
    private final String email;
    private final TipoSocio tipoSocio;
    private final List<String> isbnPrestamosActivos;

    public Socio(int id, String nombre, String dni, String email, TipoSocio tipoSocio) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.tipoSocio = tipoSocio;
        this.isbnPrestamosActivos = new ArrayList<>();
    }

    public int id() { return id; }
    public String nombre() { return nombre; }
    public String dni() { return dni; }
    public String email() { return email; }
    public TipoSocio tipoSocio() { return tipoSocio; }
    public List<String> getIsbnPrestamosActivos() { return isbnPrestamosActivos; }

    public boolean puedeTomarPrestamo() {
        return isbnPrestamosActivos.size() < tipoSocio.getLimitePrestamos();
    }

    public void agregarPrestamo(String isbn) {
        isbnPrestamosActivos.add(isbn);
    }

    public void devolverPrestamo(String isbn) {
        isbnPrestamosActivos.remove(isbn);
    }
}