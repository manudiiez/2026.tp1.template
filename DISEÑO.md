## Decisiones de Diseño

### ¿Por qué `record` para LibroFisico/Ebook/Prestamo?
Son datos inmutables: una vez creado un prestamo no cambia su ISBN ni fecha de inicio.
Los records generan automaticamente constructor, getters, equals y hashCode.

### ¿Por qué `class` para Socio?
El socio necesita modificar su lista de prestamos activos (agregar y quitar).
Los records son inmutables, por lo tanto se necesita una clase tradicional.

### ¿Como se aplica SOLID?
- **SRP**: Cada clase tiene una sola responsabilidad (Repositorio = datos, Servicio = logica).
- **OCP**: Para agregar un nuevo tipo de recurso solo creo un nuevo record que implementa `Recurso`.
- **LSP**: `LibroFisico` y `Ebook` pueden usarse en cualquier lugar donde se espera un `Recurso`.
- **ISP**: `RecursoRepository` extiende `Repository` con metodos especificos, sin forzar implementaciones vacias.
- **DIP**: Los servicios dependen de interfaces (`RecursoRepository`), no de implementaciones concretas.