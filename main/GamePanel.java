package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.GuardedObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ai.PathFinder;
import entity.Entity;
import entity.GreenGoblin;
import entity.Monster;
import entity.Neil;
import entity.Player;

import tile.TileManager;



public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS 
    final int orginalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = orginalTileSize * scale;  // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 25;
    public final int maxWorldRow = 25;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;
    
    // SPAWN SETTINGS
    public int spawnX = 0;
    public int spawnY = 0;
    
    // OBJECTS
    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public PathFinder pFinder = new PathFinder(this);
    public Entity obj[] = new Entity[10];
    public Monster monster[] = new Monster[20];
    public ArrayList<Entity> projectileList = new ArrayList<Entity>();
    public ArrayList<Entity> entityList = new ArrayList<Entity>();

    //public Player player = new Player(this,keyH,mouseL);
    public Player player = new Neil(this, keyH);


    int prevPosX = player.worldX / tileSize;
    int prevPosY = player.worldY / tileSize;

    //Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int waveOver = 3;
    public final int characterState = 4;


    


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setMonster();
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                //1 UPDATE: update information such as character positions
                update();

                

                // 2 DRAW: draw the screen with the updated information
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                
                /*System.out.println("FPS: " + drawCount);
                System.out.println("Mouse position: " + mouseL.mouseX + " " + mouseL.mouseY);
                System.out.println("Player position: " + player.worldX + " " + player.worldY);
                System.out.println("Player position: " + player.worldX/tileSize + " " + player.worldY/tileSize);
                System.out.println(ui.commandNum);*/
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        if(gameState == playState){
            player.update();
            /*spawnMonster();*/
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].update();
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i).alive == true) {
                    projectileList.get(i).update();
                }
                if(projectileList.get(i).alive == false){
                    projectileList.remove(i);
                }
            }
            if (allMonstersDefeated()) {
                gameState = waveOver;
            }

        }
        if(gameState == pauseState){
            //pause menu
        }
        
    }
    public boolean allMonstersDefeated() {
        for (Monster monster : monster) {
            if (monster != null && monster.alive) {
                return false;
            }
        }
        return true;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        if(gameState == titleState){
            //title screen
            ui.draw(g2);
        }
        else{
            tileM.draw(g2);

            entityList.add(player);
        
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    //System.out.println("Dodan na entity list");
                    entityList.add(projectileList.get(i));
                }
            }

            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            entityList.clear();
            ui.draw(g2);
        }
        
        g2.dispose();
    }

    public void spawnMonster() {
        Random rand = new Random();
        for (int i = 0; i < monster.length; i++) {
            if (monster[i] == null) {
                int spawnX, spawnY;
                do {
                    int randomIndex = rand.nextInt(tileM.spawnTile.size());
                    int[] randomArray = tileM.spawnTile.get(randomIndex);
                    spawnX = randomArray[0] * tileSize;
                    spawnY = randomArray[1] * tileSize;
                } while (isOccupied(spawnX, spawnY));
                //System.out.println(spawnX + " " + spawnY);
                monster[i] = new GreenGoblin(this);
                ((Monster)monster[i]).setDefaultValues(spawnX, spawnY);
                Point playerPos = player.getPlayerPosition();
                int playerX = playerPos.x / tileSize;
                int playerY = playerPos.y / tileSize;

                


                //System.out.println(monster[i].worldX + " " + monster[i].worldY);
                break;
            }
        }
    }
    
    private boolean isOccupied(int x, int y) {
        Point playerPos = player.getPlayerPosition();
        if (playerPos.x == x && playerPos.y == y) {
            return true;
        }
    
        for (Entity e : monster) {
            if (e instanceof Monster) {
                Monster m = (Monster) e;
                Point monsterPos = m.getMonsterPosition();
                if (monsterPos.x == x && monsterPos.y == y) {
                    return true;
                }
            }
        }
    
        return false;
    }

}
