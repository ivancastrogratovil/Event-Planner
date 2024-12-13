import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class EventPlannerMain {

    private static ArrayList<Event> events = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nBienvenido a Event Planner. Seleccione una opción:");
            System.out.println("[1] Añadir evento");
            System.out.println("[2] Borrar evento");
            System.out.println("[3] Listar eventos");
            System.out.println("[4] Marcar/desmarcar tarea de un evento como completada");
            System.out.println("[5] Salir");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        añadirEvento(scanner);
                        break;
                    case 2:
                        borrarEvento(scanner);
                        break;
                    case 3:
                        listarEventos();
                        break;
                    case 4:
                        marcarDesmarcarTarea(scanner);
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número entre 1 y 5.");
            }
        }

        scanner.close();
    }

    private static void añadirEvento(Scanner scanner) {
        System.out.println("Introduzca el título del evento:");
        String title = scanner.nextLine();

        // Verificar si ya existe un evento con ese título
        Event eventoExistente = null;
        for (Event event : events) {
            if (event.getTitle().equalsIgnoreCase(title)) {
                eventoExistente = event;
                break;
            }
        }

        if (eventoExistente != null) {
            System.out.println("Ya existe un evento con ese título. Seleccione una nueva fecha para continuar.");

            System.out.println("Fecha del evento existente: " + eventoExistente.getDate());
            System.out.println("Introduzca la nueva fecha (yyyy-mm-dd):");

            try {
                LocalDate nuevaFecha = LocalDate.parse(scanner.nextLine());

                // Verificar si la nueva fecha es diferente a la existente
                if (!eventoExistente.getDate().equals(nuevaFecha)) {
                    System.out.println("Seleccione la prioridad (HIGH, MEDIUM, LOW):");
                    Event.Priority priority = Event.Priority.valueOf(scanner.nextLine().toUpperCase());

                    Event nuevoEvento = new Event(title, nuevaFecha, priority);

                    System.out.println("¿Desea agregar tareas al nuevo evento? (sí/no)");
                    if (scanner.nextLine().equalsIgnoreCase("sí")) {
                        boolean agregarOtraTarea = true;
                        while (agregarOtraTarea) {
                            System.out.println("Introduzca la descripción de la tarea:");
                            String tarea = scanner.nextLine();
                            nuevoEvento.agregarTarea(tarea);

                            System.out.println("¿Desea agregar otra tarea? (sí/no)");
                            agregarOtraTarea = scanner.nextLine().equalsIgnoreCase("sí");
                        }
                    }

                    events.add(nuevoEvento);
                    System.out.println("Evento añadido exitosamente.");
                } else {
                    System.out.println("La fecha seleccionada es igual a la fecha del evento existente.");
                }
            } catch (Exception e) {
                System.out.println("Fecha no válida. Por favor intente nuevamente.");
            }
        } else {
            System.out.println("Introduzca la fecha del evento (yyyy-mm-dd):");
            try {
                LocalDate date = LocalDate.parse(scanner.nextLine());

                System.out.println("Seleccione la prioridad (HIGH, MEDIUM, LOW):");
                Event.Priority priority = Event.Priority.valueOf(scanner.nextLine().toUpperCase());

                Event event = new Event(title, date, priority);

                System.out.println("¿Desea agregar tareas al evento? (sí/no)");
                if (scanner.nextLine().equalsIgnoreCase("sí")) {
                    boolean agregarOtraTarea = true;
                    while (agregarOtraTarea) {
                        System.out.println("Introduzca la descripción de la tarea:");
                        String tarea = scanner.nextLine();
                        event.agregarTarea(tarea);

                        System.out.println("¿Desea agregar otra tarea? (sí/no)");
                        agregarOtraTarea = scanner.nextLine().equalsIgnoreCase("sí");
                    }
                }

                events.add(event);
                System.out.println("Evento añadido exitosamente.");
            } catch (Exception e) {
                System.out.println("Fecha no válida. Por favor intente nuevamente.");
            }
        }
    }

    private static void borrarEvento(Scanner scanner) {
        System.out.println("Introduzca el título del evento a borrar:");
        String title = scanner.nextLine();

        // Filtrar eventos por título
        ArrayList<Event> eventosConTitulo = new ArrayList<>();
        for (Event event : events) {
            if (event.getTitle().equalsIgnoreCase(title)) {
                eventosConTitulo.add(event);
            }
        }

        // Verificar coincidencia exacta en título del evento
        if (eventosConTitulo.isEmpty()) {
            System.out.println("No se encontró el evento con ese título.");
        } else if (eventosConTitulo.size() == 1) {
            events.remove(eventosConTitulo.get(0));
            System.out.println("Evento borrado exitosamente.");
        } else {
            System.out.println("Hay varios eventos con ese título. Por favor ingrese la fecha del evento que desea borrar:");

            for (int i = 0; i < eventosConTitulo.size(); i++) {
                System.out.println((i + 1) + ". " + eventosConTitulo.get(i).getDate());
            }

            try {
                int opcion = Integer.parseInt(scanner.nextLine()) - 1;

                if (opcion >= 0 && opcion < eventosConTitulo.size()) {
                    events.remove(eventosConTitulo.get(opcion));
                    System.out.println("Evento borrado exitosamente.");
                } else {
                    System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número válido.");
            }
        }
    }


    private static void listarEventos() {
        for (Event event : events) {
            System.out.println(event);
        }
    }

    private static void marcarDesmarcarTarea(Scanner scanner) {
        System.out.println("Introduzca el título del evento sobre el que quiere trabajar:");
        String title = scanner.nextLine();

        ArrayList<Event> eventosConTitulo = new ArrayList<>();
        for (Event event : events) {
            if (event.getTitle().equalsIgnoreCase(title)) {
                eventosConTitulo.add(event);
            }
        }

        if (eventosConTitulo.isEmpty()) {
            System.out.println("No se encontró ningún evento con ese título.");
        } else if (eventosConTitulo.size() == 1) {
            Event event = eventosConTitulo.get(0);
            manejarTareas(event, scanner);
        } else {
            System.out.println("Hay varios eventos con ese título. Por favor ingrese la fecha del evento que desea seleccionar:");

            for (int i = 0; i < eventosConTitulo.size(); i++) {
                System.out.println((i + 1) + ". " + eventosConTitulo.get(i).getDate());
            }

            try {
                int opcion = Integer.parseInt(scanner.nextLine()) - 1;

                if (opcion >= 0 && opcion < eventosConTitulo.size()) {
                    Event event = eventosConTitulo.get(opcion);
                    manejarTareas(event, scanner);
                } else {
                    System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor intente nuevamente.");
            }
        }
    }

    private static void manejarTareas(Event event, Scanner scanner) {
        System.out.println("Tareas del evento:");
        for (int i = 0; i < event.getTasks().size(); i++) {
            System.out.println((i + 1) + ". " + event.getTasks().get(i));
        }

        System.out.println("Seleccione la tarea a modificar/desmarcar (número de la tarea) o ingrese 0 para salir:");
        try {
            int opcion = Integer.parseInt(scanner.nextLine()) - 1;

            if (opcion >= 0 && opcion < event.getTasks().size()) {
                EventTask task = event.getTasks().get(opcion);
                task.toggleCompletion();  // Método para alternar el estado de completado
                System.out.println("Tarea modificada.");
            } else if (opcion == -1) {
                System.out.println("Regresando al menú.");
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Por favor intente nuevamente.");
        }
    }

}
