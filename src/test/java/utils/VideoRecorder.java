package utils;

import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class VideoRecorder {

    private static ScreenRecorder screenRecorder;
    private static final int MAX_RECORDINGS = 0;

    public static void startRecording(String methodName) throws Exception {
        File file = new File("./recordings/");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);

        deleteOldVideos(); // 🔥 delete older files before recording

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new SpecializedScreenRecorder(gc, captureSize, file, methodName);
        screenRecorder.start();
    }

    public static void stopRecording() throws Exception {
        screenRecorder.stop();
    }

    private static void deleteOldVideos() {
        File folder = new File("./recordings/");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".avi"));

        if (files != null && files.length > MAX_RECORDINGS) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
            for (int i = MAX_RECORDINGS; i < files.length; i++) {
                files[i].delete(); // delete older files
            }
        }
    }
}
