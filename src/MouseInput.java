package game.src.main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

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
                if (my >= 250 && my <= 300){
                    //pressed start button
                    MazeGame.state = MazeGame.STATE.GAME;
                }
            }

            if (mx >= MazeGame.WIDTH / 2 +110 && mx <= MazeGame.WIDTH / 2 + 220){
                if (my >= 350 && my <= 400){
                    //pressed options button
                    MazeGame.state = MazeGame.STATE.OPTIONS;
                }
            }
            if (mx >= MazeGame.WIDTH / 2 +110 && mx <= MazeGame.WIDTH / 2 + 220){
                if (my >= 450 && my <= 500){
                    //pressed exit button
                    System.exit(1);
                }
            }
        }
        else if (MazeGame.getState() == MazeGame.STATE.GAME){ //in-game menu
            if (mx >= MazeGame.WIDTH + 260 && mx <= MazeGame.WIDTH + 310){
                if (my >= 10 && my <= 35){
                    //pressed options button
                    if (MazeGame.openBox) MazeGame.openBox = false;
                    else MazeGame.openBox = true;
                }
            }

            if (mx >= MazeGame.WIDTH + 280 && mx <= MazeGame.WIDTH + 330){
                if (my >= 60 && my <= 85){
                    //pressed exit button
                    System.exit(1);

                }
            }
            if (mx >= MazeGame.WIDTH + 275 && mx <= MazeGame.WIDTH + 275 +60){
                if (my >= 90 && my <= 115){
                    //pressed menu button
                    MazeGame.state = MazeGame.STATE.MENU;

                }
            }
            if (mx >= MazeGame.WIDTH + 275 && mx <= MazeGame.WIDTH + 275 +60){
                if (my >= 120 && my <= 145){
                    //pressed restart button
                    MazeGame.reset = true;

                    MazeGame.state = MazeGame.STATE.GAME;

                }
            }

        }
        else if (MazeGame.getState() == MazeGame.STATE.OPTIONS) { //options menu

            //if "main menu" is pressed, return to main menu
            if (mx >= MazeGame.WIDTH / 2  && mx <= MazeGame.WIDTH / 2 + 120){
                if (my >= 250 && my <= 300){
                    // pressed awsd
                    MazeGame.options.arrowsPressed = false;
                }
            }

            if (mx >= MazeGame.WIDTH && mx <= MazeGame.WIDTH + 120){
                if (my >= 250 && my <= 300){
                    // pressed arrows
                    MazeGame.options.arrowsPressed = true;
                }
            }

            if (mx >= MazeGame.WIDTH && mx <= MazeGame.WIDTH + 320){
                if (my >= 450 && my <= 500){
                    // pressed return to main menu
                    MazeGame.state = MazeGame.STATE.MENU;
                }
            }
        }
        else if (MazeGame.state == MazeGame.STATE.GAMEOVER) { //game over screen (either you died or you completed a level)
            if (mx >= MazeGame.WIDTH / 2 + 220 && mx <= MazeGame.WIDTH / 2 + 320){
                if (my >= 375 && my <= 375 + 50){
                    //pressed exit button
                    System.exit(1);
                }
            }

            if (mx >= MazeGame.WIDTH / 2 + 60 && mx <= MazeGame.WIDTH / 2 + 200){
                if (my >= 375 && my <= 375 + 50){
                    //pressed restart button

                    MazeGame.reset = true;
                    MazeGame.state = MazeGame.STATE.GAME;

                }
            }

            if (mx >= MazeGame.WIDTH / 2 + 35 && mx <= MazeGame.WIDTH / 2 + 35 + 310){
                if (my >= 300 && my <= 350){
                    //pressed return to main menu button
                    MazeGame.state = MazeGame.STATE.MENU;

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
