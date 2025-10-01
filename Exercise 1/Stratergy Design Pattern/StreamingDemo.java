interface QualityStrategy {
    String describe();
    int recommendedBitrateKbps(); // simple metric
}

class LowQuality implements QualityStrategy {
    public String describe() { return "Low (240p)"; }
    public int recommendedBitrateKbps() { return 300; }
}
class MediumQuality implements QualityStrategy {
    public String describe() { return "Medium (480p)"; }
    public int recommendedBitrateKbps() { return 1000; }
}
class HighQuality implements QualityStrategy {
    public String describe() { return "High (720p/1080p)"; }
    public int recommendedBitrateKbps() { return 3000; }
}

class VideoPlayer {
    private QualityStrategy strategy;
    void setStrategy(QualityStrategy s){ this.strategy = s; }
    void play(String video) {
        if (strategy == null) System.out.println("No quality selected. Falling back to Medium.");
        System.out.println("Playing '" + video + "' at " +
                (strategy == null ? new MediumQuality().describe() : strategy.describe()) +
                " (~" + (strategy == null ? new MediumQuality().recommendedBitrateKbps() : strategy.recommendedBitrateKbps()) + " kbps)");
    }

    // simple auto-select based on measured bandwidth (kbps)
    void adaptToBandwidth(int bandwidthKbps) {
        if (bandwidthKbps < 600) setStrategy(new LowQuality());
        else if (bandwidthKbps < 2000) setStrategy(new MediumQuality());
        else setStrategy(new HighQuality());
        System.out.println("[Player] Adapted strategy to bandwidth: " + bandwidthKbps + " kbps -> " + strategy.describe());
    }
}

// Demo
public class StreamingDemo {
    public static void main(String[] args) {
        VideoPlayer player = new VideoPlayer();
        player.adaptToBandwidth(450);  // poor network
        player.play("Nature Documentary");

        player.adaptToBandwidth(1500); // decent network
        player.play("Nature Documentary");

        player.adaptToBandwidth(5000); // great network
        player.play("Nature Documentary");
    }
}
