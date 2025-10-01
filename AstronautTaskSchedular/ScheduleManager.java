
import java.util.ArrayList;
import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance = null;
    private List<Task> tasks;
    private List<ScheduleObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<Task>();
        observers = new ArrayList<ScheduleObserver>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) instance = new ScheduleManager();
        return instance;
    }

    public void addObserver(ScheduleObserver o) { observers.add(o); }

    // Add task if no conflict; else notify observers and throw exception
    public void addTask(Task t) {
        Task conflict = findConflict(t);
        if (conflict != null) {
            for (ScheduleObserver o : observers) o.onConflict(t, conflict);
            throw new IllegalArgumentException("Task conflicts with existing task: " + conflict.getDescription());
        }
        tasks.add(t);
        for (ScheduleObserver o : observers) o.onTaskAdded(t);
    }

    // Remove by id
    public void removeTask(String id) {
        Task found = null;
        for (int i = 0; i < tasks.size(); i++) {
            Task tt = tasks.get(i);
            if (tt.getId().equals(id)) { found = tt; tasks.remove(i); break; }
        }
        if (found == null) throw new IllegalArgumentException("Task not found: " + id);
        for (ScheduleObserver o : observers) o.onTaskRemoved(id);
    }

    // View tasks sorted by start time (simple selection sort like approach)
    public List<Task> getAllTasksSorted() {
        List<Task> copy = new ArrayList<Task>();
        for (Task t : tasks) copy.add(t);
        // simple bubble sort (beginner-friendly)
        for (int i = 0; i < copy.size(); i++) {
            for (int j = 0; j < copy.size() - 1 - i; j++) {
                if (copy.get(j).getStartMinutes() > copy.get(j+1).getStartMinutes()) {
                    Task tmp = copy.get(j); copy.set(j, copy.get(j+1)); copy.set(j+1, tmp);
                }
            }
        }
        return copy;
    }

    // Check overlap with any existing task. Return first conflicting task or null.
    private Task findConflict(Task candidate) {
        for (Task t : tasks) {
            if (overlaps(candidate.getStartMinutes(), candidate.getEndMinutes(),
                         t.getStartMinutes(), t.getEndMinutes())) {
                return t;
            }
        }
        return null;
    }

    // overlap if start < otherEnd && otherStart < end
    private boolean overlaps(int aStart, int aEnd, int bStart, int bEnd) {
        return aStart < bEnd && bStart < aEnd;
    }

    // Simple edit: replace fields, but check conflict excluding the task itself
    public void editTask(String id, String newDesc, int newStart, int newEnd, String newPriority) {
        Task target = null;
        for (Task t : tasks) if (t.getId().equals(id)) { target = t; break; }
        if (target == null) throw new IllegalArgumentException("Task not found: " + id);

        // Temporarily remove
        tasks.remove(target);
        Task candidate = new Task(newDesc, newStart, newEnd, newPriority);
        Task conflict = findConflict(candidate);
        if (conflict != null) {
            // put original back and notify
            tasks.add(target);
            for (ScheduleObserver o : observers) o.onConflict(candidate, conflict);
            throw new IllegalArgumentException("Edit conflicts with task: " + conflict.getDescription());
        }
        // apply edits
        target.setDescription(newDesc);
        target.setStartMinutes(newStart);
        target.setEndMinutes(newEnd);
        target.setPriority(newPriority);
        tasks.add(target);
        for (ScheduleObserver o : observers) o.onTaskUpdated(target);
    }

    public void markCompleted(String id) {
        Task found = null;
        for (Task t : tasks) if (t.getId().equals(id)) { found = t; break; }
        if (found == null) throw new IllegalArgumentException("Task not found: " + id);
        found.markCompleted();
        for (ScheduleObserver o : observers) o.onTaskUpdated(found);
    }

    public List<Task> viewByPriority(String priority) {
        List<Task> out = new ArrayList<Task>();
        for (Task t : tasks) if (t.getPriority().equalsIgnoreCase(priority)) out.add(t);
        return out;
    }
}
