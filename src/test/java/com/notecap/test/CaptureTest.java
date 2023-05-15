package com.notecap.client;

import com.notecap.capture.Capture;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.io.File;

public class CaptureTest {
    @Test
    public void captureImageAndSaveInLocalStorage()
    {
        Capture capture = new Capture();
        String filename = capture.captureImageAndSaveInLocalStorage(".", "", "png");

        if (filename != null) {
            File file = new File(filename);
            file.delete();
        }

        assertNotNull(filename);
    }
}
