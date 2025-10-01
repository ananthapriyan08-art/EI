package smarthome;

import java.util.Map;
import java.util.logging.Logger;

public class Light implements Device {
    private final String id;
    private volatile boolean on = false;
    private final Logger logger = Logger.getLogger(Light.class.getName());

    public Light(String id) { this.id = id; }

    public synchronized void turnOn() { on = true; logger.info("Light " + id + " turned ON"); }
    public synchronized void turnOff() { on = false; logger.info("Light " + id + " turned OFF"); }
    public boolean isOn() { return on; }

    @Override public String getId() { return id; }
    @Override public String getType() { return "light"; }
    @Override public Map<String, Object> status() { return Map.of("id", id, "type", getType(), "on", on); }
}
