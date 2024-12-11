import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Timer extends DatabaseHandler {
    private LocalDateTime startTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    // Start-Methode
    public void startTimer() {
        startTime = LocalDateTime.now();
        System.out.println("Timer started at: " + startTime.format(formatter));
    }

    // Stop-Methode
    public void stopTimer(String project, String theme) {
        if (startTime == null) {
            System.out.println("Timer was not started.");
            return;
        }

        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        double workDuration = duration.toMinutes() / 60.0;

        String date = startTime.toLocalDate().toString();
        String startTimeFormatted = startTime.format(formatter);
        String endTimeFormatted = endTime.format(formatter);

        addTimeEntry(date, startTimeFormatted, endTimeFormatted, project, workDuration, theme);
        System.out.println("Timer stopped at: " + endTimeFormatted + ", Duration: " + workDuration + " hours");

        // Reset startTime
        startTime = null;
    }
}

