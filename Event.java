import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    private final String title;
    private final LocalDate date;
    private final Priority priority;
    private final ArrayList<EventTask> tasks;

    // Enum para la prioridad
    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    // Constructor
    public Event(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.tasks = new ArrayList<>();
    }

    // Método para agregar tarea
    public void agregarTarea(String text) {
        tasks.add(new EventTask(text));
    }

    // Métodos getter
    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    // Método para obtener las tareas
    public ArrayList<EventTask> getTasks() {
        return tasks;
    }

    // Método toString para mostrar los detalles del evento
    @Override
    public String toString() {
        int tareasCompletadas = (int) tasks.stream().filter(EventTask::isCompleted).count();
        return title + ", " + date + ", " + priority +
                " - Tareas Completadas: " + tareasCompletadas + " de " + tasks.size();
    }
}
