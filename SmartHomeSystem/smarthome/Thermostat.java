package smarthome;

import java.util.Map;
import java.util.logging.Logger;

public class Thermostat implements Device {
    private final String id;
    private volatile double temperature;
    private final Logger logger = Logger.getLogger(Thermostat.class.getName());

    public Thermostat(String id, double initialTemp) { this.id = id; this.temperature = initialTemp; }

    public synchronized void setTemperature(double t) { temperature = t; logger.info("Thermostat " + id + " set to " + t); }
    public synchronized double getTemperature() { return temperature; }

    @Override public String getId() { return id; }
    @Override public String getType() { return "thermostat"; }
    @Override public Map<String, Object> status() { return Map.of("id", id, "type", getType(), "temperature", temperature); }
}
