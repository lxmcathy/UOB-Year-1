package com.bham.pij.assignments.edgedetector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;



public class GUI extends Application {

    private static final String TITLE = "Hello";

    private Stage stage;
    private VBox root;
    private Image originalImage;
    private ImageView imageView;
    private EdgeDetector edgeDetector;
    private MenuItem openItem;
    private MenuItem closeItem;
    private MenuItem detectionItem;
    private MenuItem revertItem;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new VBox();
        edgeDetector = new EdgeDetector();

        stage.setTitle(TITLE);

        Menu fileMenu = new Menu("File");
        openItem = new MenuItem("Open");
        closeItem = new MenuItem("Close");
        fileMenu.getItems().addAll(openItem, closeItem);

        Menu toolMenu = new Menu("Tools");
        detectionItem = new MenuItem("Edge Detection");
        revertItem = new MenuItem("Revert");
        toolMenu.getItems().addAll(detectionItem, revertItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, toolMenu);

        root.getChildren().add(menuBar);

        setUp();

        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    private void setUp() {
        closeItem.setDisable(true);
        detectionItem.setDisable(true);
        revertItem.setDisable(true);

        openItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                loadImageFile(file);

                closeItem.setDisable(false);
                detectionItem.setDisable(false);
                revertItem.setDisable(true);
            }
        });

        closeItem.setOnAction(e -> {
            root.getChildren().remove(imageView);

            imageView.setImage(null);
            imageView = null;

            closeItem.setDisable(true);
            detectionItem.setDisable(true);
            revertItem.setDisable(true);
        });

        detectionItem.setOnAction(e -> {
            Image filterImage = edgeDetector.filterImage(imageView.getImage());
            imageView.setImage(filterImage);

            detectionItem.setDisable(true);
            closeItem.setDisable(false);
            revertItem.setDisable(false);
        });

        revertItem.setOnAction(e -> {
            imageView.setImage(originalImage);

            revertItem.setDisable(true);
            detectionItem.setDisable(false);
            closeItem.setDisable(false);
        });
    }

    private void loadImageFile(File file) {
        originalImage = new Image("file:" + file.getPath());

        if (null != imageView) {
            imageView.setImage(originalImage);
        } else {
            imageView = new ImageView(originalImage);
            root.getChildren().add(imageView);
        }
    }

public static void main(String[] args) {
    launch(args);
}
}

