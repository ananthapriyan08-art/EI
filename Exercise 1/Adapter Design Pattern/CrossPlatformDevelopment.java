// Platform-specific implementations
interface Camera {
    void capture();
}

class AndroidCamera implements Camera {
    public void capture() {
        System.out.println("Capturing photo using Android camera.");
    }
}

class IOSCamera implements Camera {
    public void capture() {
        System.out.println("Capturing photo using iOS camera.");
    }
}

// Adapter Interface
interface CameraAdapter {
    void takePicture();
}

// Concrete Adapter for Android
class AndroidCameraAdapter implements CameraAdapter {
    private Camera androidCamera;

    public AndroidCameraAdapter(Camera androidCamera) {
        this.androidCamera = androidCamera;
    }

    public void takePicture() {
        androidCamera.capture();
    }
}

// Concrete Adapter for iOS
class IOSCameraAdapter implements CameraAdapter {
    private Camera iosCamera;

    public IOSCameraAdapter(Camera iosCamera) {
        this.iosCamera = iosCamera;
    }

    public void takePicture() {
        iosCamera.capture();
    }
}

// Client Code
public class CrossPlatformDevelopment {
    public static void main(String[] args) {
        Camera androidCamera = new AndroidCamera();
        CameraAdapter androidAdapter = new AndroidCameraAdapter(androidCamera);
        androidAdapter.takePicture();

        Camera iosCamera = new IOSCamera();
        CameraAdapter iosAdapter = new IOSCameraAdapter(iosCamera);
        iosAdapter.takePicture();
    }
}
