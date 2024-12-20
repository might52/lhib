package org.might;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
/*        Text text = new Text();
        StringBuilder sb = new StringBuilder("Test java fx text\n");
        getParameters()
                .getRaw()
                .forEach(param ->
                        sb.append("Parameter raw: ").append(param).append("\n")
                );
        text.setText(sb.toString());
        text.setLayoutX(100);
        text.setLayoutY(100);
        Group root = new Group();
        root.getChildren().add(text);
        Scene scene = new Scene(root);
        Label label = new Label("Hello!");
        Button button = new Button("Click");
        button.setOnAction(event -> button
                .setText("You've clicked!: " + event.getEventType()));
        Group group = new Group(button);
        FlowPane root = new FlowPane(label, group);
*/
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("JavaFX test application");
        stage.setWidth(400);
        stage.setHeight(400);
        stage.centerOnScreen();
        stage.show();
    }
}