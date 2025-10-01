class Logger {
    private static Logger instance;

    private Logger() {
        System.out.println("Logger initialized.");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}

// Client Code
public class LoggerClient {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("App started");

        Logger logger2 = Logger.getInstance();
        logger2.log("User logged in");

        System.out.println("Same instance? " + (logger1 == logger2)); // true
    }
}
