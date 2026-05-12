package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            Files.createDirectories(Path.of("screenshots"));

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            Path destination = Path.of("screenshots", testName + "_" + timestamp + ".png");

            Files.copy(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return destination.toString();
        } catch (IOException e) {
            return "Could not save screenshot: " + e.getMessage();
        }
    }
}