
public class ConsoleObserver implements ScheduleObserver {
    public void onTaskAdded(Task t) {
        System.out.println("Task added: " + t);
    }
    public void onTaskRemoved(String id) {
        System.out.println("Task removed: " + id);
    }
    public void onConflict(Task attempted, Task existing) {
        System.out.println("ERROR: Task '" + attempted.getDescription() + "' conflicts with '" + existing.getDescription() + "'");
    }
    public void onTaskUpdated(Task t) {
        System.out.println("Task updated: " + t);
    }
}
