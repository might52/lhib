package org.might;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class Main extends Application {

    public static void main(String[] args) {
        // to make enabled java script parsing, possible way to enable it
        // -Djavafx.allowjs=true
//        System.getProperties().setProperty("javafx.allowjs", "true");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/fxml/main.fxml"));
        GridPane root = loader.load();
        root.setGridLinesVisible(true);
        root.setMinHeight(1000);
        root.setMinWidth(1000);
        Button btn = new Button("Hello!");
        btn.setOnAction((ActionEvent event) -> {
            btn.setText("You've clicked button!");
        });
        root.getCellBounds(0, 2);
        root.add(btn, 0, 0);
        root.add(getTreeView(), 0, 1);
        root.add(getTreeTableView(), 0, 2);
        stage.setScene(new Scene(root, 1000, 1000));
        stage.setTitle("JavaFX test application");
        stage.centerOnScreen();
        stage.show();
    }

    private TreeTableView<String> getTreeTableView() {
        TreeTableView<String> treeTableView = new TreeTableView<>(getTree());
        treeTableView.setEditable(true);
        treeTableView.setShowRoot(true);
        treeTableView.setMinHeight(500);
        treeTableView.setMinWidth(1000);

        List<TreeTableColumn<String, String>> columns = new ArrayList<>();
        TreeTableColumn<String, String> col1 = new TreeTableColumn<>("Id");
        col1.setPrefWidth(50);
        col1.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> param) ->
                new ReadOnlyStringWrapper(param.getValue().getValue())
        );
        columns.add(col1);
        TreeTableColumn<String, String> col2 = new TreeTableColumn<>("Name");
        col2.setPrefWidth(100);
        col2.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> param) ->
                new ReadOnlyStringWrapper(param.getValue().getValue())
        );
        columns.add(col2);
        TreeTableColumn<String, String> col3 = new TreeTableColumn<>("Type");
        col3.setPrefWidth(100);
        col3.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> param) ->
                new ReadOnlyStringWrapper(param.getValue().getValue())
        );
        columns.add(col3);
        TreeTableColumn<String, String> col4 = new TreeTableColumn<>("Description");
        col4.setPrefWidth(500);
        col4.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> param) ->
                new ReadOnlyStringWrapper(param.getValue().getValue())
        );
        columns.add(col4);
        treeTableView.getColumns().setAll(columns);
        treeTableView.getTreeItem(0).getChildren().forEach(el -> el.getChildren().add(getTree()));
        return treeTableView;
    }

    private TreeView<String> getTreeView() {
        TreeView<String> tree = new TreeView<>(getTree());
        tree.setMinHeight(300);
        tree.setMinWidth(400);
        tree.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println(event.getClickCount() + " " + event.getTarget().toString() + " "
                    + event.getTarget().getClass() + " " + event.getButton());
        });
        tree.getTreeItem(0).getChildren().forEach(el -> el.getChildren().add(getTree()));
        return tree;
    }

    private TreeItem<String> getTree() {
        TreeItem<String> root = new TreeItem<>("Hello root element",
                new ImageView(new Image("/icons/angle-right.png"))
        );
        root.setExpanded(false);
        IntStream.range(0, 4).forEach(level -> {
            TreeItem<String> item;
            if (level == 0) {
                item =
                        new TreeItem<>("Hello Tree item " + level,
                                new ImageView(new Image("/icons/list.png"))
                        );
                item.setExpanded(false);
            } else {
                item =
                        new TreeItem<>("Hello Tree item " + level,
                                new ImageView(new Image("/icons/apps.png"))
                        );
            }
            root.getChildren().add(item);
        });
        return root;
    }
}