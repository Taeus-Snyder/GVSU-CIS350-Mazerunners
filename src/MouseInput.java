package game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //    public Rectangle startButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 150, 120, 50);
        //    public Rectangle optionsButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 250, 120, 50);
        //    public Rectangle exitButton = new Rectangle(MazeGame.WIDTH / 2 + 110, 350, 120, 50);
        if (MazeGame.getState() == MazeGame.STATE.MENU){
            //start button
            if (mx >= MazeGame.WIDTH / 2 +110 && mx <= MazeGame.WIDTH / 2 + 220){
                if (my >= 150 && my <= 200){
                    //pressed start button
                    MazeGame.state = MazeGame.STATE.GAME;
                }
            }

            //options button


            //exit button
            if (mx >= MazeGame.WIDTH / 2 +110 && mx <= MazeGame.WIDTH / 2 + 220){
                if (my >= 350 && my <= 400){
                    //pressed exit button
                    System.exit(1);
                }
            }

        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
