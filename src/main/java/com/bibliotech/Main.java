package com.bibliotech;

import com.bibliotech.exception.BibliotecaException;
import com.bibliotech.model.*;
import com.bibliotech.repository.InMemoryPrestamoRepository;
import com.bibliotech.repository.InMemoryRecursoRepository;
import com.bibliotech.repository.InMemorySocioRepository;
import com.bibliotech.service.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        var recursoRepo  = new InMemoryRecursoRepository();
        var socioRepo    = new InMemorySocioRepository();
        var prestamoRepo = new InMemoryPrestamoRepository();

        RecursoService  recursoService  = new RecursoServiceImpl(recursoRepo);
        SocioService    socioService    = new SocioServiceImpl(socioRepo);
        PrestamoService prestamoService = new PrestamoServiceImpl(recursoRepo, socioRepo, prestamoRepo);

        while (continuar) {
            System.out.println("\n=== BiblioTech ===");
            System.out.println("1. Registrar libro fisico");
            System.out.println("2. Registrar e-book");
            System.out.println("3. Buscar recurso");
            System.out.println("4. Registrar socio");
            System.out.println("5. Realizar prestamo");
            System.out.println("6. Registrar devolucion");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1 -> registrarLibroFisico(scanner, recursoService);
                case 2 -> registrarEbook(scanner, recursoService);
                case 3 -> buscarRecurso(scanner, recursoService);
                case 4 -> registrarSocio(scanner, socioService);
                case 5 -> realizarPrestamo(scanner, prestamoService);
                case 6 -> registrarDevolucion(scanner, prestamoService);
                case 0 -> continuar = false;
                default -> System.out.println("Opcion invalida.");
            }
        }
        System.out.println("Hasta luego.");
    }

    static void registrarLibroFisico(Scanner scanner, RecursoService recursoService) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Anio: ");
        int anio = Integer.parseInt(scanner.nextLine());
        System.out.println("Categorias: " + java.util.Arrays.toString(CategoriaLibro.values()));
        System.out.print("Categoria: ");
        CategoriaLibro categoria = CategoriaLibro.valueOf(scanner.nextLine().toUpperCase());
        recursoService.registrarLibroFisico(new LibroFisico(isbn, titulo, autor, anio, categoria, true));
        System.out.println("Libro fisico registrado correctamente.");
    }

    static void registrarEbook(Scanner scanner, RecursoService recursoService) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Anio: ");
        int anio = Integer.parseInt(scanner.nextLine());
        System.out.println("Categorias: " + java.util.Arrays.toString(CategoriaLibro.values()));
        System.out.print("Categoria: ");
        CategoriaLibro categoria = CategoriaLibro.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("URL de descarga: ");
        String url = scanner.nextLine();
        recursoService.registrarEbook(new Ebook(isbn, titulo, autor, anio, categoria, url));
        System.out.println("E-book registrado correctamente.");
    }

    static void buscarRecurso(Scanner scanner, RecursoService recursoService) {
        System.out.println("Buscar por: 1=Titulo  2=Autor  3=Categoria");
        System.out.print("Opcion: ");
        int criterio = Integer.parseInt(scanner.nextLine());
        List<Recurso> resultados;
        switch (criterio) {
            case 1 -> {
                System.out.print("Titulo: ");
                resultados = recursoService.buscarPorTitulo(scanner.nextLine());
            }
            case 2 -> {
                System.out.print("Autor: ");
                resultados = recursoService.buscarPorAutor(scanner.nextLine());
            }
            case 3 -> {
                System.out.println("Categorias: " + java.util.Arrays.toString(CategoriaLibro.values()));
                System.out.print("Categoria: ");
                CategoriaLibro cat = CategoriaLibro.valueOf(scanner.nextLine().toUpperCase());
                resultados = recursoService.buscarPorCategoria(cat);
            }
            default -> {
                System.out.println("Criterio invalido.");
                return;
            }
        }
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            resultados.forEach(r -> System.out.println("  - [" + r.isbn() + "] " + r.titulo() + " - " + r.autor()));
        }
    }

    static void registrarSocio(Scanner scanner, SocioService socioService) {
        try {
            System.out.print("ID (numero): ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("DNI: ");
            String dni = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Tipo (ESTUDIANTE / DOCENTE): ");
            TipoSocio tipo = TipoSocio.valueOf(scanner.nextLine().toUpperCase());
            socioService.registrarSocio(id, nombre, dni, email, tipo);
            System.out.println("Socio registrado correctamente.");
        } catch (BibliotecaException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void realizarPrestamo(Scanner scanner, PrestamoService prestamoService) {
        try {
            System.out.print("ISBN del libro: ");
            String isbn = scanner.nextLine();
            System.out.print("ID del socio: ");
            int socioId = Integer.parseInt(scanner.nextLine());
            prestamoService.realizarPrestamo(isbn, socioId);
            System.out.println("Prestamo registrado correctamente.");
        } catch (BibliotecaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void registrarDevolucion(Scanner scanner, PrestamoService prestamoService) {
        try {
            System.out.print("ID del prestamo: ");
            int prestamoId = Integer.parseInt(scanner.nextLine());
            System.out.print("ID del socio: ");
            int socioId = Integer.parseInt(scanner.nextLine());
            prestamoService.registrarDevolucion(prestamoId, socioId);
            System.out.println("Devolucion registrada correctamente.");
        } catch (BibliotecaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
