package com.bibliotech.service;

import com.bibliotech.exception.DniDuplicadoException;
import com.bibliotech.model.Socio;
import com.bibliotech.model.TipoSocio;
import com.bibliotech.repository.SocioRepository;
import java.util.List;
import java.util.Optional;

public class SocioServiceImpl implements SocioService {
    private final SocioRepository socioRepository;

    public SocioServiceImpl(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    @Override
    public void registrarSocio(int id, String nombre, String dni, String email, TipoSocio tipo)
            throws DniDuplicadoException {
        if (!emailValido(email)) {
            throw new IllegalArgumentException("El email no tiene un formato valido: " + email);
        }
        if (socioRepository.buscarPorDni(dni).isPresent()) {
            throw new DniDuplicadoException(dni);
        }
        socioRepository.guardar(new Socio(id, nombre, dni, email, tipo));
    }

    @Override
    public Optional<Socio> buscarPorDni(String dni) {
        return socioRepository.buscarPorDni(dni);
    }

    @Override
    public List<Socio> listarTodos() {
        return socioRepository.buscarTodos();
    }

    private boolean emailValido(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}