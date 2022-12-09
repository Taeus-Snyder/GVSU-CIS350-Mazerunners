package game.src.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MazeLogic {
    private char[][] maze;
    private int rows = 0;
    private int cols = 0;
    private int mazeX;
    private int mazeY;
    final int wallHeight = 16;
    final int wallWidth = 16;

    public void readMaze(String mazefile) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(mazefile));
        String s;

        int rows = 0;
        int cols = 0;
        while ((s = r.readLine()) != null) {
            cols = 0;
            for (char c : s.toCharArray()) {
                //System.out.println(c);
                maze[rows][cols] = c;
                cols++;
            }
            rows++;
        }

        maze[16][0]='.';
        maze[16][cols-1]='.';

    }

    public MazeLogic(String mazefile, int mazeX, int mazeY) throws IOException {
        this.mazeX = mazeX;
        this.mazeY = mazeY;
        //Read maze txt file to determine size
        generateMaze();
        BufferedReader r = new BufferedReader(new FileReader(mazefile));
        String s;
        while ((s = r.readLine()) != null) {
            if(cols == 0){
                cols = s.length();
            }
            //System.out.println(s);
            rows++;
        }
        maze = new char[rows][cols]; //Initialize maze array based on size
        readMaze(mazefile); // read maze into class
    }

    public String toString(){
        String r = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(Character.toString(maze[i][j]));
            }
            System.out.println();
        }
        return r;


    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public void render (Graphics g) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream("src\\assets\\brickTexture1.png"));
        int x = mazeX;
        int y = mazeY;
        for (int i = 0; i < getRows(); i++) {
            y = (wallHeight*i);
            for (int j = 0; j < getCols(); j++) {
                x = (wallWidth*j);
                if(maze[i][j] == '|'){
                    g.drawImage(image, x, y, null);
                }
                if(maze[i][j] == '.') g.fillRect(x,y,wallWidth,wallHeight);
            }

        }

    }

    public void generateMaze() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "python walls.py > maze.txt");
        builder.directory(new File("src\\assets"));
        Process process = builder.start();

    }

    public void pwd(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);
    }

    public int getMazeX() {
        return mazeX;
    }

    public int getMazeY() {
        return mazeY;
    }

    public int getWallHeight() {
        return wallHeight;
    }

    public int getWallWidth() {
        return wallWidth;
    }
}
