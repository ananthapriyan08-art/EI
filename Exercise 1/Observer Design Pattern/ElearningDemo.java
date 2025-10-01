import java.util.*;

interface Student {
    void notifyUpdate(CourseUpdate update);
}

class CourseUpdate {
    enum Kind {NEW_LECTURE, NEW_QUIZ, DEADLINE_CHANGE}
    final Kind kind;
    final String message;
    CourseUpdate(Kind kind, String message){ this.kind = kind; this.message = message; }
}

class Course {
    private final String title;
    private final List<Student> subscribers = new ArrayList<>();
    Course(String title){ this.title = title; }
    void subscribe(Student s){ subscribers.add(s); }
    void unsubscribe(Student s){ subscribers.remove(s); }

    void publish(CourseUpdate update){
        System.out.println("[Course - " + title + "] Publishing: " + update.kind + " -> " + update.message);
        for (Student s : subscribers) s.notifyUpdate(update);
    }
}

// Concrete student
class Learner implements Student {
    private final String name;
    Learner(String name){ this.name = name; }
    @Override
    public void notifyUpdate(CourseUpdate update) {
        System.out.println(name + " received: [" + update.kind + "] " + update.message);
    }
}

// Demo
public class ElearningDemo {
    public static void main(String[] args) {
        Course javaCourse = new Course("Advanced Java");

        Student alice = new Learner("Alice");
        Student bob = new Learner("Bob");
        javaCourse.subscribe(alice);
        javaCourse.subscribe(bob);

        javaCourse.publish(new CourseUpdate(CourseUpdate.Kind.NEW_LECTURE, "Lecture 7: Streams and Parallelism uploaded."));
        javaCourse.publish(new CourseUpdate(CourseUpdate.Kind.NEW_QUIZ, "Quiz 3 available until next Sunday."));
        javaCourse.unsubscribe(bob);
        javaCourse.publish(new CourseUpdate(CourseUpdate.Kind.DEADLINE_CHANGE, "Assignment 1 deadline extended by 2 days."));
    }
}
