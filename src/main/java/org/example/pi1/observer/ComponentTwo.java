package org.example.pi1.observer;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.pi1.subject.Subject;
import org.example.pi1.subject.TimeServer;

public class ComponentTwo implements IObserver {

    private final int interval;
    private final ProgressBar progressBar;
    private int lastTrigger = 0;

    public ComponentTwo(Pane pane, int interval) {
        this.interval = interval;

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(180);

        VBox box = new VBox(10, progressBar);
        box.setPrefSize(200, 150);
        box.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: black;");
        box.setTranslateX(10);
        box.setTranslateY(10);

        pane.getChildren().add(box);
    }

    @Override
    public void update(Subject subject) {
        TimeServer server = (TimeServer) subject;
        int t = server.getState();

        Platform.runLater(() -> {
            double value = (t % interval) / (double) interval;
            progressBar.setProgress(value);
        });

        if (t != 0 && t % interval == 0 && t != lastTrigger) {
            lastTrigger = t;
            triggerAlarm();
        }
    }

    private void triggerAlarm() {
        Platform.runLater(() -> {
            // Визуальная тряска
            TranslateTransition shake = new TranslateTransition(Duration.millis(50), progressBar);
            shake.setByX(10);
            shake.setAutoReverse(true);
            shake.setCycleCount(6);
            shake.play();

            // Сообщение
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Будильник");
            alert.setHeaderText(null);
            alert.setContentText("Сработал будильник!");
            alert.show();
        });
    }
}
