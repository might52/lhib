package org.might;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Text text = new Text();
        StringBuilder sb = new StringBuilder("Test java fx text\n");
        getParameters()
                .getRaw()
                .forEach(param ->
                        sb.append("Parameter raw: ").append(param).append("\n")
                );
        text.setText(sb.toString());
        text.setLayoutX(100);
        text.setLayoutY(100);
//        Group root = new Group();
//        root.getChildren().add(text);
//        Scene scene = new Scene(root);
        Label label = new Label("Hello!");
        Button button = new Button("Click");
        button.setOnAction(event -> button
                .setText("You've clicked!: " + event.getEventType()));
        Group group = new Group(button);
        FlowPane root = new FlowPane(label, group);
        Scene scene = new Scene(root, 300, 150);
        stage.setScene(scene);
        stage.setTitle("JavaFX test application");
        stage.setWidth(400);
        stage.setHeight(400);
        stage.centerOnScreen();
        stage.show();
    }
}