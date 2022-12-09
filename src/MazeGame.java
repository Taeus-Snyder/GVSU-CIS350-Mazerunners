package game.src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;
import java.util.function.BooleanSupplier;


public class MazeGame extends Canvas implements Runnable {

    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 360;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Maze Game";

    public boolean running = false;
    private boolean mazeRendered = false;
    private boolean touching = false;

    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;


    private Player p;

    private Enemy e;

    private PowerUps ups;

    private Present present1;
    private Present present2;
    private Present present3;

    private Health health;

    private Menu menu;

    private InGameMenu inGameMenu;

    private GameOver gameOver;

    public static Options options;

    private MazeLogic mazeGen;

    public static Boolean dead = false;

    public static Boolean reset = false;

    public MazeLogic originalMaze;

    public enum STATE{
        MENU,
        GAME,
        OPTIONS,
        GAMEOVER
    }

    public static STATE getState(){
        return state;
    }

    public static STATE state = STATE.MENU;


    public void init(){
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage("spritesheet16.png");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        try {
            mazeGen = new MazeLogic( "src\\assets\\maze.txt", 0, 0);
            originalMaze = mazeGen;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());
        p = new Player(mazeGen.getMazeX(), mazeGen.getMazeY() + (mazeGen.getWallHeight()*16), this);
        e = new Enemy(mazeGen.getMazeX() + ((mazeGen.getCols()-1)*16) , mazeGen.getMazeY() + (mazeGen.getWallHeight()*16), this);
        ups = new PowerUps(mazeGen.getMazeX() + (16), mazeGen.getMazeY() + 32, this);
        health = new Health(mazeGen.getMazeX() + (16* (mazeGen.getCols()-2)),  mazeGen.getMazeY() + (32), this);

        present1 = new Present(mazeGen.getMazeX() + 16, mazeGen.getMazeY() + (16*(mazeGen.getRows()-2)), this);
        present2 = new Present(mazeGen.getMazeX() + (16*(mazeGen.getCols()-2)), mazeGen.getMazeY() + (16*(mazeGen.getRows()-2)), this);
        present3 = new Present(mazeGen.getMazeX() + ((mazeGen.getCols()-1)*16) , mazeGen.getMazeY() + (mazeGen.getWallHeight()*16), this);

        menu = new Menu();
        inGameMenu = new InGameMenu();
        options = new Options();
        gameOver = new GameOver();

    }


    private synchronized void start(){
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if (!running)
            return;

        running = false;
        try{
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }


    public void run(){
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1){
                tick();
                updates ++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;

            }
        }
        stop();
    }

    public void reset(){

        mazeGen = originalMaze;

        p = new Player(mazeGen.getMazeX(), mazeGen.getMazeY() + (mazeGen.getWallHeight()*16), this);
        e = new Enemy(mazeGen.getMazeX() + ((mazeGen.getCols()-1)*16) , mazeGen.getMazeY() + (mazeGen.getWallHeight()*16), this);
        ups = new PowerUps(mazeGen.getMazeX() + (16), mazeGen.getMazeY() + 32, this);
        health = new Health(mazeGen.getMazeX() + (16* (mazeGen.getCols()-2)),  mazeGen.getMazeY() + (32), this);

        present1 = new Present(mazeGen.getMazeX() + 16, mazeGen.getMazeY() + (16*(mazeGen.getRows()-2)), this);
        present2 = new Present(mazeGen.getMazeX() + (16*(mazeGen.getCols()-2)), mazeGen.getMazeY() + (16*(mazeGen.getRows()-2)), this);
        present3 = new Present(mazeGen.getMazeX() + ((mazeGen.getCols()-1)*16) , mazeGen.getMazeY() + (mazeGen.getWallHeight()*16), this);

        reset = false;
    }
    private void tick(){
        if (state == STATE.GAME){
            p.tick();
            e.tick();

        }

    }

    private void render(){
        if (reset) reset();
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        if (state == STATE.MENU || state == STATE.OPTIONS) {
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);
        }

        if (state == STATE.GAME){
            try {
                mazeGen.render(g);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ups.render(g);
            health.render(g);
            inGameMenu.render(g);
            present1.render(g);
            present2.render(g);
            present3.render(g);
            p.render(g, mazeGen.getMazeX(), mazeGen.getMazeY());
            e.render(g, mazeGen.getMazeX(), mazeGen.getMazeY());
        }
        else if (state == STATE.MENU) {
            reset = true;
            menu.render(g);
        }
        else if (state == STATE.OPTIONS) {
            options.render(g);
        }
        else if (state == STATE.GAMEOVER) {
            gameOver.render(g);
        }


        if (ups.isTouching(p.getBounds())) {
            ups.setPuIsVisable(false);
            p.setVolMod(2);
        }

        if(present1.isTouching((int)p.getX(), (int)p.getY())){
            present1.setPresentIsVisable(false);
            p.incPresents();
        }

        if(present2.isTouching((int)p.getX(), (int)p.getY())){
            present2.setPresentIsVisable(false);
            p.incPresents();

        }

        if(present3.isTouching((int)p.getX(), (int)p.getY())){
            present3.setPresentIsVisable(false);
            p.incPresents();

        }

        if(p.getpresentCount() == 3 && p.getrPos() == 16 && p.getcPos() == 27){
            state = STATE.GAMEOVER;
        }


        if (health.isTouching(p.getBounds())) {
            health.setHealthIsVisable(false);
            health.setHealth(1);
        }

        if (p.getX() == e.getX() && p.getY() == e.getY() && touching == false) {
            touching = true;
            health.setHealth(-1);
            e.setX(mazeGen.getMazeX() + ((mazeGen.getCols()-1)*16));
            e.setY(mazeGen.getMazeY() + (mazeGen.getWallHeight()*16));
            dead = Integer.parseInt(health.getHealth()) == 0;
            if (dead) {
                state = STATE.GAMEOVER;
            }
        }else if(p.getX() != e.getX() && p.getY() != e.getY()){
            touching = false;
        }

        if (options.arrowsPressed){
            options.up = KeyEvent.VK_UP;
            options.down = KeyEvent.VK_DOWN;
            options.right = KeyEvent.VK_RIGHT;
            options.left = KeyEvent.VK_LEFT;
        }
        else {
            options.up = KeyEvent.VK_W;
            options.down = KeyEvent.VK_S;
            options.right = KeyEvent.VK_D;
            options.left = KeyEvent.VK_A;
        }

        if (reset) reset();
        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Random rand = new Random();

        if (state == STATE.GAME) {
            int direction = rand.nextInt(4);
            int r = p.getrPos();
            int c = p.getcPos();

            if (key == options.right){
                if(c != 27 && mazeGen.getMazeAt(r,c + 1) != '|')p.incCPos();
            }
            else if (key == options.left) {
                if(c != 0 && mazeGen.getMazeAt(r,c - 1) != '|')p.decCPos();
            }
            else if (key == options.down){
                if(r != 31 && mazeGen.getMazeAt(r + 1,c) != '|')p.incRPos();
            }
            else if (key == options.up){
                if(r != 0 && mazeGen.getMazeAt(r - 1,c) != '|')p.decRPos();
            }


            if (key == options.right || key == options.left || key == options.up || key == options.down ) {
                r = this.e.getrPos();
                c = this.e.getcPos();
                if (direction == 0){
                    if(c != 27 && mazeGen.getMazeAt(r,c + 1) != '|')this.e.incCPos();
                }
                else if (direction == 1){
                    if(c != 0 && mazeGen.getMazeAt(r,c - 1) != '|')this.e.decCPos();
                }
                else if (direction == 2){
                    if(r != 31 && mazeGen.getMazeAt(r + 1,c) != '|')this.e.incRPos();
                }
                else if (direction == 3){
                    if(r != 0 && mazeGen.getMazeAt(r - 1, c) != '|')this.e.decRPos();
                }
            }
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if (key == options.right){
            p.setVolX(0);
        } else if (key == options.left){
            p.setVolX(0);
        } else if (key == options.down){
            p.setVolY(0);
        } else if (key == options.up){
            p.setVolY(0);
        }

        this.e.setVolX(0);
        this.e.setVolY(0);
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }


    public static void main(String[] args) {
        MazeGame game = new MazeGame();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game. setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

    }

}
