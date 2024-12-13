

public class EventTask {
    private String text;
    private boolean isCompleted;

    // Constructor
    public EventTask(String text) {
        this.text = text;
        this.isCompleted = false;
    }

    public void toggleCompletion() {
        this.isCompleted = !this.isCompleted;
    }

    // Método para obtener el estado de completación
    public boolean isCompleted() {
        return isCompleted;
    }

    // Método toString para mostrar los detalles de la tarea
    @Override
    public String toString() {
        return text + (isCompleted ? " (Completada)" : " (Pendiente)");
    }
}
