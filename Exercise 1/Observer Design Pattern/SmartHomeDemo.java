import java.util.*;

interface SmartObserver {
    void onEvent(SmartEvent event);
}

class SmartEvent {
    enum Type {TEMPERATURE_CHANGE, MOTION_DETECTED, DOOR_OPEN}
    final Type type;
    final Map<String,Object> payload;
    SmartEvent(Type type, Map<String,Object> payload) {
        this.type = type; this.payload = payload;
    }
}

class SmartHub {
    private final List<SmartObserver> observers = new ArrayList<>();
    void register(SmartObserver o){ observers.add(o); }
    void unregister(SmartObserver o){ observers.remove(o); }

    void emit(SmartEvent event){
        System.out.println("[Hub] Emitting event: " + event.type);
        for (SmartObserver o : observers) o.onEvent(event);
    }
}

// Concrete devices
class AirConditioner implements SmartObserver {
    @Override
    public void onEvent(SmartEvent event) {
        if (event.type == SmartEvent.Type.TEMPERATURE_CHANGE) {
            double temp = (double) event.payload.get("temp");
            if (temp > 26.0) System.out.println("AC: It's hot ("+temp+"°C). Turning ON.");
            else System.out.println("AC: Temp " + temp + "°C. Turning OFF or idle.");
        }
    }
}

class SmartLight implements SmartObserver {
    @Override
    public void onEvent(SmartEvent event) {
        if (event.type == SmartEvent.Type.MOTION_DETECTED) {
            boolean night = (boolean) event.payload.getOrDefault("night", false);
            if (night) System.out.println("Light: Motion at night — turning ON.");
        }
    }
}

class SecurityCamera implements SmartObserver {
    @Override
    public void onEvent(SmartEvent event) {
        if (event.type == SmartEvent.Type.MOTION_DETECTED || event.type == SmartEvent.Type.DOOR_OPEN) {
            System.out.println("Camera: Recording triggered by " + event.type);
        }
    }
}

// Demo
public class SmartHomeDemo {
    public static void main(String[] args) {
        SmartHub hub = new SmartHub();
        hub.register(new AirConditioner());
        hub.register(new SmartLight());
        hub.register(new SecurityCamera());

        Map<String,Object> tempPayload = Map.of("temp", 28.3);
        hub.emit(new SmartEvent(SmartEvent.Type.TEMPERATURE_CHANGE, tempPayload));

        Map<String,Object> motionPayload = new HashMap<>();
        motionPayload.put("night", true);
        hub.emit(new SmartEvent(SmartEvent.Type.MOTION_DETECTED, motionPayload));

        hub.emit(new SmartEvent(SmartEvent.Type.DOOR_OPEN, Collections.emptyMap()));
    }
}
