package game.src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;


public class MazeGame extends Canvas implements Runnable {

    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 360;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Maze Game";

    public boolean running = false;
    private boolean mazeRendered = false;

    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;


    private Player p;

    private Enemy e;

    private PowerUps ups;

    private Health health;

    private Menu menu;

    private InGameMenu inGameMenu;

    public static Options options;

    private MazeLogic mazeGen;

    public static enum STATE{
        MENU,
        GAME,
        OPTIONS
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());
        p = new Player(mazeGen.getMazeX(), mazeGen.getMazeY() + (mazeGen.getWallHeight()*16), this);
        e = new Enemy(mazeGen.getMazeX() + ((mazeGen.getCols()-1)*16) , mazeGen.getMazeY() + (mazeGen.getWallHeight()*16), this);
        ups = new PowerUps(64, 64, this);
        health = new Health(256, 64, this);

        menu = new Menu();
        inGameMenu = new InGameMenu();
        options = new Options();

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

    private void tick(){
        if (state == STATE.GAME){
            p.tick();
            e.tick();

        }

    }

    private void render(){

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
            p.render(g);
            e.render(g);


        }
        else if (state == STATE.MENU) {
            menu.render(g);
        }
        else if (state == STATE.OPTIONS) {
            options.render(g);
        }

        if (ups.isTouching(p.getBounds())) {
            ups.setPuIsVisable(false);
            p.setVolMod(2);
        }

        if (health.isTouching(p.getBounds())) {
            health.setHealthIsVisable(false);
            health.setHealth(1);
        }

        if (p.isTouching(e.getBounds())) {
            health.setHealth(-1);
            e.setX(mazeGen.getMazeX() + ((mazeGen.getCols()-1)*16));
            e.setY(mazeGen.getMazeY() + (mazeGen.getWallHeight()*16));
            boolean dead = Integer.parseInt(health.getHealth()) == 0;
            if (dead) {
                dead = false; //made to compiling
                //game state = end screen
            }
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

        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Random rand = new Random();

        if (state == STATE.GAME) {
            int direction = rand.nextInt(4);

            if (key == options.right) p.setVolX(4);
            else if (key == options.left) p.setVolX(-4);
            else if (key == options.down) p.setVolY(4);
            else if (key == options.up) p.setVolY(-4);


            if (key == options.right || key == options.left || key == options.up || key == options.down ) {
                if (direction == 0) this.e.setVolX(4);
                else if (direction == 1) this.e.setVolX(-4);
                else if (direction == 2) this.e.setVolY(4);
                else if (direction == 3) this.e.setVolY(-4);
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
