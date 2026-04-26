Scanner scanner = new Scanner(System.in);
boolean continuar = true;

while (continuar) {
        System.out.println("\n=== BiblioTech ===");
    System.out.println("1. Registrar libro fisico");
    System.out.println("2. Registrar e-book");
    System.out.println("3. Buscar libro");
    System.out.println("4. Registrar socio");
    System.out.println("5. Realizar prestamo");
    System.out.println("6. Registrar devolucion");
    System.out.println("0. Salir");
    System.out.print("Opcion: ");

int opcion = scanner.nextInt();
    scanner.nextLine(); // consume el enter

    switch (opcion) {
        case 1 -> registrarLibro(scanner, recursoService);
        case 0 -> continuar = false;
        // ... etc
        }
        }