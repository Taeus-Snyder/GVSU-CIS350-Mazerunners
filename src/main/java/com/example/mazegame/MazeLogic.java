package com.example.mazegame;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MazeLogic {
    char[][] maze;
    int rows = 0;
    int cols = 0;
    public void readMaze(String mazefile) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(mazefile));
        r = new BufferedReader(new FileReader(mazefile));
        String s;

        int rows = 0;
        int cols = 0;
        while((s = r.readLine()) != null){
            cols = 0;
            for (char c : s.toCharArray()){
                //System.out.println(c);
                maze[rows][cols] = c;
                cols++;
            }
            rows++;
        }
    }
    public MazeLogic(String mazefile) throws IOException {
        //Read maze txt file to determine size
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
}
