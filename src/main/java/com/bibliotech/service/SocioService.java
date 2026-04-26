package com.bibliotech.service;

import com.bibliotech.model.Socio;
import com.bibliotech.model.TipoSocio;
import java.util.List;
import java.util.Optional;

public interface SocioService {
    void registrarSocio(int id, String nombre, String dni, String email, TipoSocio tipo)
            throws com.bibliotech.exception.DniDuplicadoException;
    Optional<Socio> buscarPorDni(String dni);
    List<Socio> listarTodos();
}