package com.example.mazegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class MazeApplication extends Application {
    public static MazeLogic m;
    static final int wallHeight = 16;
    static final int wallWidth = 16;
    @Override
    public void start(Stage stage) throws IOException {

        Canvas canvas = new Canvas(800, 800);
        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Set line width
        gc.setLineWidth(2.0);
        // Set the Color
        gc.setStroke(Color.GREEN);
        // Set fill color
        gc.setFill(Color.LIGHTGRAY);

        // Create the Pane
        Pane root = new Pane();


        int posX = 100;
        int posY = 100;

        for (int i = 0; i < m.getRows(); i++) {
            posY = (100 + wallHeight*i);
            for (int j = 0; j < m.getCols(); j++) {
                posX = (100 + wallWidth*j);
                if(m.maze[i][j] == '|') drawImage(gc, root, posX, posY);
                if(m.maze[i][j] == '.') drawPixel(gc, posX, posY);
            }

        }




        // Add the Canvas to the Pane
        root.getChildren().add(canvas);
        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Maze Game");
        // Display the Stage
        stage.show();

    }

    public void drawPixel(GraphicsContext gc, int x, int y){
        gc.fillRect(x,y,wallWidth,wallHeight);
    }

    public void drawImage(GraphicsContext gc, Pane r, int x, int y) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src\\assets\\brickTexture1.png"));
        ImageView imageView = new ImageView(image);

        imageView.setX(x);
        imageView.setY(y);
        r.getChildren().add(imageView);
    }


    public static void main(String[] args) throws IOException {
        m = new MazeLogic("src\\assets\\maze.txt");
        System.out.println(m);

        m.generateMaze();
        launch();
    }
}