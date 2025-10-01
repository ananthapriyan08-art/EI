
public class Task {
    private static int COUNTER = 1;
    private final String id;
    private String description;
    private int startMinutes; // minutes since midnight
    private int endMinutes;
    private String priority; // "Low","Medium","High"
    private boolean completed;

    public Task(String description, int startMinutes, int endMinutes, String priority) {
        this.id = "T" + (COUNTER++);
        this.description = description;
        this.startMinutes = startMinutes;
        this.endMinutes = endMinutes;
        this.priority = priority;
        this.completed = false;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public int getStartMinutes() { return startMinutes; }
    public int getEndMinutes() { return endMinutes; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return completed; }

    public void setDescription(String d) { description = d; }
    public void setStartMinutes(int m) { startMinutes = m; }
    public void setEndMinutes(int m) { endMinutes = m; }
    public void setPriority(String p) { priority = p; }
    public void markCompleted() { completed = true; }

    private String minutesToHHMM(int m) {
        int hh = m / 60;
        int mm = m % 60;
        return String.format("%02d:%02d", hh, mm);
    }

    public String toString() {
        String done = completed ? " (Done)" : "";
        return id + " " + minutesToHHMM(startMinutes) + "-" + minutesToHHMM(endMinutes)
                + " : " + description + " [" + priority + "]" + done;
    }
}
