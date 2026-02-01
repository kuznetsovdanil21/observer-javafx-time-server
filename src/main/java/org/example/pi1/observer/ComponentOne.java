package org.example.pi1.observer;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import org.example.pi1.subject.Subject;
import org.example.pi1.subject.TimeServer;

public class ComponentOne implements IObserver {

    private final Label timeLabel;

    public ComponentOne(Pane pane) {
        timeLabel = new Label("0");
        timeLabel.setFont(new Font("Arial Black", 48));

        StackPane wrapper = new StackPane(timeLabel);
        wrapper.setPrefSize(200, 150);
        wrapper.setStyle("-fx-background-color: #222; -fx-border-color: black;");
        wrapper.setAlignment(Pos.CENTER);

        pane.getChildren().add(wrapper);
    }

    @Override
    public void update(Subject subject) {
        TimeServer server = (TimeServer) subject;
        Platform.runLater(() ->
                timeLabel.setText(server.getState() + " s")
        );
    }
}
