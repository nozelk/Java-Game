package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.MouseLisener;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    MouseLisener mouseL;

    public Player(GamePanel gp, KeyHandler keyH, MouseLisener mouseL){

        this.gp = gp;
        this.keyH = keyH;
        this.mouseL = mouseL;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        directions = "down";

        attacking = false;
        attackDuration = 15;
        attackCounter = 0;


    }

    public void getPlayerImage(){
        try {
            attack1up = ImageIO.read(getClass().getResourceAsStream("/res/player/Down1Strike.PNG"));
            attack2up = ImageIO.read(getClass().getResourceAsStream("/res/player/Down2Strike.PNG"));
            attack1right = ImageIO.read(getClass().getResourceAsStream("/res/player/Right1Strike.PNG"));
            attack2right = ImageIO.read(getClass().getResourceAsStream("/res/player/Right2Strike.PNG"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        try{
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Down1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Down2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Down1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Down2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Right2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if (mouseL.leftButtonPressed){
            attack();
        }
        if (attacking){
            attackCounter++;
            if (attackCounter >= attackDuration){
                attacking = false;
                attackCounter = 0;
            }
        }

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){


            if(keyH.upPressed && keyH.rightPressed) {
                directions = "up-right";
                x += speed * Math.sqrt(2) / 2;
                y -= speed * Math.sqrt(2) / 2;
            } else if(keyH.upPressed && keyH.leftPressed) {
                directions = "up-left";
                x -= speed * Math.sqrt(2) / 2;
                y -= speed * Math.sqrt(2) / 2;
            } else if(keyH.downPressed && keyH.rightPressed) {
                directions = "down-right";
                x += speed * Math.sqrt(2) / 2;
                y += speed * Math.sqrt(2) / 2;
            } else if(keyH.downPressed && keyH.leftPressed) {
                directions = "down-left";
                x -= speed * Math.sqrt(2) / 2;
                y += speed * Math.sqrt(2) / 2;
            }
            else if(keyH.upPressed == true){
                directions = "up";
                y -= speed; 
            }
            else if(keyH.downPressed == true){
                directions = "down";
                y += speed;
            }
            else if(keyH.leftPressed == true){
                directions = "left";
                x -= speed;
            }
            else if(keyH.rightPressed == true){
                directions = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
    
        if(attacking) {
            // Izberite sliko napada glede na smer
            switch (directions) {
                case "up":
                    image = (spriteNum == 1) ? attack1up : attack1up;
                    break;
                case "down":
                    image = (spriteNum == 1) ? attack1up : attack1up;
                    break;
                case "left":
                    image = (spriteNum == 1) ? attack1up : attack1up;
                    break;
                case "right":
                    image = (spriteNum == 1) ? attack1right : attack2right;
                    break;
                case "up-right":
                    image = (spriteNum == 1) ? attack1right : attack2right;
                    break;
                case "up-left":
                    image = (spriteNum == 1) ? attack1up : attack1up;
                    break;
                case "down-right":
                    image = (spriteNum == 1) ? attack1right : attack2right;
                    break;
                case "down-left":
                    image = (spriteNum == 1) ? attack1up : attack1up;
                    break;
            }
        } else {
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
                case "up-right":
                    image = (spriteNum == 1) ? right1 : right2;
                    break;
                case "up-left":
                    image = (spriteNum == 1) ? left1 : left2;
                    break;
                case "down-right":
                    image = (spriteNum == 1) ? right1 : right2;
                    break;
                case "down-left":
                    image = (spriteNum == 1) ? down1 : down2;
                    break;
            }
        }
    
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
    
    public void attack(){
        attacking = true;
        attackCounter = 0;
    }
    
}
