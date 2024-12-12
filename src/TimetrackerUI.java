import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TimeTrackerUI extends Application {
    private Timer timer = new Timer();

    @Override
    public void start(Stage primaryStage) {
        // UI-Komponenten erstellen
        TextField projectField = new TextField();
        projectField.setPromptText("Project Name");

        TextField themeField = new TextField();
        themeField.setPromptText("Theme");

        Button startButton = new Button("Start Timer");
        Button stopButton = new Button("Stop Timer");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        // Button-Aktionen
        startButton.setOnAction(e -> timer.startTimer());
        stopButton.setOnAction(e -> {
            String project = projectField.getText();
            String theme = themeField.getText();
            timer.stopTimer(project, theme);
            outputArea.setText(""); // Aktualisiere die Anzeige
            timer.showAllEntries();
        });

        // Layout
        VBox layout = new VBox(10, projectField, themeField, startButton, stopButton, outputArea);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Time Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        timer.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

