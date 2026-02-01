package org.example.pi1.observer;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.pi1.subject.Subject;

import java.util.LinkedList;
import java.util.List;

public class ComponentThree implements IObserver {

    private final Pane pane;
    private final List<Rectangle> snake = new LinkedList<>();

    private double dx = 10;
    private double dy = 0;

    public ComponentThree(Pane pane) {
        this.pane = pane;

        for (int i = 0; i < 3; i++) {
            Rectangle part = new Rectangle(20, 20, Color.GREEN);
            part.setX(50 - i * 20);
            part.setY(60);
            snake.add(part);
            pane.getChildren().add(part);
        }
    }

    @Override
    public void update(Subject subject) {
        Platform.runLater(this::move);
    }

    private void move() {
        Rectangle head = snake.get(0);
        double newX = head.getX() + dx;
        double newY = head.getY() + dy;

        if (newX <= 0 || newX >= pane.getWidth() - 20) {
            dx = -dx;
        }

        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).setX(snake.get(i - 1).getX());
            snake.get(i).setY(snake.get(i - 1).getY());
        }

        head.setX(head.getX() + dx);
        head.setY(head.getY() + dy);

        // Рост змейки каждые 10 шагов
        if (Math.random() < 0.05) {
            Rectangle tail = snake.get(snake.size() - 1);
            Rectangle newPart = new Rectangle(20, 20, Color.GREEN);
            newPart.setX(tail.getX());
            newPart.setY(tail.getY());
            snake.add(newPart);
            pane.getChildren().add(newPart);
        }
    }
}
