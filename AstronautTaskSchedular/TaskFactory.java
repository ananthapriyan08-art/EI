
public class TaskFactory {
    // parse HH:MM into minutes since midnight; throws IllegalArgumentException on bad format
    public static int parseTimeToMinutes(String hhmm) {
        if (hhmm == null) throw new IllegalArgumentException("Time required");
        String s = hhmm.trim();
        String[] parts = s.split(":");
        if (parts.length != 2) throw new IllegalArgumentException("Invalid time format, use HH:MM");
        int hh = Integer.parseInt(parts[0]);
        int mm = Integer.parseInt(parts[1]);
        if (hh < 0 || hh > 23 || mm < 0 || mm > 59) throw new IllegalArgumentException("Invalid hour/minute");
        return hh * 60 + mm;
    }

    public static Task create(String description, String startStr, String endStr, String priority) {
        int start = parseTimeToMinutes(startStr);
        int end = parseTimeToMinutes(endStr);
        if (end <= start) throw new IllegalArgumentException("End time must be after start time");
        String pr = priority.trim();
        if (!(pr.equalsIgnoreCase("Low") || pr.equalsIgnoreCase("Medium") || pr.equalsIgnoreCase("High")))
            throw new IllegalArgumentException("Priority must be Low, Medium, or High");
        return new Task(description.trim(), start, end, capitalize(pr));
    }

    private static String capitalize(String s) {
        s = s.toLowerCase();
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }
}
