package org.example.pi1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.example.pi1.observer.*;
import org.example.pi1.subject.TimeServer;

public class HelloController {

    @FXML private Label stateLabel;

    @FXML private Pane panel1;
    @FXML private Pane panel2;
    @FXML private Pane panel3;

    private TimeServer timeServer;

    @FXML
    public void initialize() {
        timeServer = new TimeServer();

        timeServer.attach(new ComponentOne(panel1));
        timeServer.attach(new ComponentTwo(panel2, 10));
        timeServer.attach(new ComponentThree(panel3));
    }

    @FXML
    public void startServer() {
        timeServer.start();
        stateLabel.setText("Состояние: ON");
    }

    @FXML
    public void stopServer() {
        timeServer.stop();
        stateLabel.setText("Состояние: OFF");
    }
}
