
public interface ScheduleObserver {
    void onTaskAdded(Task t);
    void onTaskRemoved(String id);
    void onConflict(Task attempted, Task existing);
    void onTaskUpdated(Task t);
}
