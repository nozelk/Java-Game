package entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


import main.GamePanel;


public class Monster extends Entity {

    String previousDirection = null;

    public final int screenX;
    public final int screenY;

    int spawnX;
    int spawnY;


    public Monster(GamePanel gp) {
        super(gp);
        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        directions = "down";

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 44;
        solidArea.height = 44;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        type = 2;


        setDefaultValues(screenX, screenY);
        getMonsterImage();
    }

    // Nastavitev privzetih vrednosti za Monster
    public void setDefaultValues(int spawnX, int spawnY) {
        // Nastavitev začetne pozicije, hitrosti, zdravja, itd.
        worldX = spawnX;
        worldY = spawnY;
        //System.out.println(worldX + " " + worldY);
        // Dodajte več atributov, če je potrebno
    }

    // Nalaganje slik za Monster
    public void getMonsterImage() {

    }
    public void setAction(){

        int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
        int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

        //System.out.println("Monster: " + worldX + " " + worldY + " Player: " + gp.player.worldX + " " + gp.player.worldY + " " + goalCol + " " + goalRow);

        searchPath(goalCol, goalRow);
        
    }
    /*@Override
    public void update(){
        int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
        int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

        searchPath(goalCol, goalRow);
    }
    @Override
    public void update() {
        // Calculate the direction from the monster to the player
        int dx = gp.player.worldX - this.worldX;
        int dy = gp.player.worldY - this.worldY;
        int move = speed;
        // Determine the direction based on dx and dy
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                directions = "right";
            } else {
                directions = "left";
            }
        } else {
            if (dy > 0) {
                directions = "down";
            } else {
                directions = "up";
            }
        }
        // Check for collision
        collisionOn = false;
        gp.cChecker.checkTile(this);
        // Move the monster if no collision
        double collisionSpeedFactor = 1.0; // Normalna hitrost, ko ni trka

        if (collisionOn) {
            move = collisionSpeed; // Zmanjšana hitrost, ko je trk
        }

        // Uporabite collisionSpeedFactor pri posodabljanju pozicije
        switch (directions) {
            case "up":
                worldY -= move;
                break;
            case "down":
                worldY += move;
                break;
            case "left":
                worldX -= move;
                break;
            case "right":
                worldX += move;
                break;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    // Metoda za risanje Monster na zaslon
    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Izberite sliko glede na trenutno stanje in smer pošasti
        switch (directions) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
            // Dodajte več primerov, če pošast napada ali se giblje v več smeri
        }

        // Izris slike pošasti na zaslonu
        //System.out.println(spawnX + " " + spawnY + " " + gp.tileSize + " " + gp.tileSize);
        int screenX = worldX - gp.player.worldX + gp.screenWidth / 2;
        int screenY = worldY - gp.player.worldY + gp.screenHeight / 2;
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }*/
    
    public Point getMonsterPosition() {
        return new Point(worldX, worldY);
    }
    // Dodatne metode, kot so napad, sledenje igralcu, itd.
}

