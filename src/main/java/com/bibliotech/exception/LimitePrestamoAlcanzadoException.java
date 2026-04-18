package com.bibliotech.exception;

public class LimitePrestamoAlcanzadoException extends BibliotecaException {
    public LimitePrestamoAlcanzadoException(int socioId) {
        super("El socio con ID " + socioId + " alcanzo el limite maximo de prestamos.");
    }
}