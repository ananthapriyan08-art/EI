interface ResponseStrategy {
    String respond(String userInput);
}

class FriendlyStrategy implements ResponseStrategy {
    public String respond(String u){ return "Hey there!" + u + " — I can help with that. What would you like to do next?"; }
}

class ProfessionalStrategy implements ResponseStrategy {
    public String respond(String u){ return "Hello. Regarding: \"" + u + "\", here is a concise answer. Let me know if you need further details."; }
}

class TechnicalStrategy implements ResponseStrategy {
    public String respond(String u){
        return "Technical note: For \"" + u + "\" — consider the following steps:\n1) Analyze inputs\n2) Validate constraints\n3) Apply algorithmic solution\n(If you want code samples, specify language.)";
    }
}

class Chatbot {
    private ResponseStrategy strategy;
    Chatbot(ResponseStrategy initial){ this.strategy = initial; }
    void setStrategy(ResponseStrategy s){ this.strategy = s; }
    String ask(String user) { return strategy.respond(user); }
}

// Demo
public class ChatbotDemo {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot(new FriendlyStrategy());
        System.out.println(bot.ask("How do I connect to a database?"));

        bot.setStrategy(new ProfessionalStrategy());
        System.out.println(bot.ask("How do I connect to a database?"));

        bot.setStrategy(new TechnicalStrategy());
        System.out.println(bot.ask("How do I connect to a database?"));
    }
}
