
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ScheduleManager manager = ScheduleManager.getInstance();

    public static void main(String[] args) {
        manager.addObserver(new ConsoleObserver());
        System.out.println("Astronaut Scheduler");
        System.out.println("Commands:");
        System.out.println("  add <description> ; <HH:MM> ; <HH:MM> ; <Low|Medium|High>");
        System.out.println("  remove <taskId>");
        System.out.println("  view");
        System.out.println("  edit <taskId> ; <description> ; <HH:MM> ; <HH:MM> ; <Priority>");
        System.out.println("  complete <taskId>");
        System.out.println("  viewprio <Low|Medium|High>");
        System.out.println("  help");
        System.out.println("  exit");
        Scanner sc = new Scanner(System.in);
        int i=0;
        while (i!=30) {
            System.out.print("> ");
            String line = sc.nextLine();
            if (line == null) break;
            line = line.trim();
            if (line.equalsIgnoreCase("exit")) break;
            if (line.equalsIgnoreCase("help")) { System.out.println("See commands above."); continue; }
            try {
                if (line.startsWith("add ")) {
                    String part = line.substring(4);
                    String[] fields = part.split("\\s*;\\s*");
                    if (fields.length != 4) { System.out.println("Usage: add description ; HH:MM ; HH:MM ; Priority"); continue; }
                    Task t = TaskFactory.create(fields[0], fields[1], fields[2], fields[3]);
                    manager.addTask(t);
                } else if (line.startsWith("remove ")) {
                    String id = line.substring(7).trim();
                    manager.removeTask(id);
                } else if (line.equalsIgnoreCase("view")) {
                    List<Task> list = manager.getAllTasksSorted();
                    if (list.size() == 0) { System.out.println("No tasks scheduled for the day."); continue; }
                    for (Task t : list) System.out.println(t);
                } else if (line.startsWith("edit ")) {
                    String part = line.substring(5);
                    String[] fields = part.split("\\s*;\\s*");
                    if (fields.length != 5) { System.out.println("Usage: edit id ; desc ; HH:MM ; HH:MM ; Priority"); continue; }
                    String id = fields[0].trim();
                    int s = TaskFactory.parseTimeToMinutes(fields[2]);
                    int e = TaskFactory.parseTimeToMinutes(fields[3]);
                    manager.editTask(id, fields[1].trim(), s, e, fields[4].trim());
                } else if (line.startsWith("complete ")) {
                    String id = line.substring(9).trim();
                    manager.markCompleted(id);
                } else if (line.startsWith("viewprio ")) {
                    String pr = line.substring(8).trim();
                    List<Task> out = manager.viewByPriority(pr);
                    if (out.size() == 0) System.out.println("No tasks with priority " + pr);
                    for (Task t : out) System.out.println(t);
                } else {
                    System.out.println("Unknown command. Type help.");
                }
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
          i++;
        }
        System.out.println("Goodbye.");
        
    }
}
