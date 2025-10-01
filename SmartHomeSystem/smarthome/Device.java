package smarthome;

import java.util.Map;

/**
 * Core device interface.
 */
public interface Device {
    String getId();
    String getType();
    Map<String, Object> status();
}
