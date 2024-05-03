package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH, MouseLisener mouseL){

        this.gp = gp;
        this.keyH = keyH;
        this.mouseL = mouseL;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){

        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 11;
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
            } else if(keyH.upPressed && keyH.leftPressed) {
                directions = "up-left";
            } else if(keyH.downPressed && keyH.rightPressed) {
                directions = "down-right";
            } else if(keyH.downPressed && keyH.leftPressed) {
                directions = "down-left";
            }
            else if(keyH.upPressed == true){
                directions = "up";
                
            }
            else if(keyH.downPressed == true){
                directions = "down";
            }
            else if(keyH.leftPressed == true){
                directions = "left";
            }
            else if(keyH.rightPressed == true){
                directions = "right";
            }


            collisionOn = false;
            gp.cChecker.checkTile(this);
            if (!collisionOn){
                switch (directions) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed;break;
                    case "up-right":
                        worldX += (int)(speed * Math.sqrt(2) / 2);
                        worldY -= (int)(speed * Math.sqrt(2) / 2);
                        break;
                    case "up-left":
                        worldX -= (int)(speed * Math.sqrt(2) / 2);
                        worldY -= (int)(speed * Math.sqrt(2) / 2);
                        break;
                    case "down-right":
                        worldX += (int)(speed * Math.sqrt(2) / 2);
                        worldY += (int)(speed * Math.sqrt(2) / 2);
                        break;
                    case "down-left":
                        worldX -= (int)(speed * Math.sqrt(2) / 2);
                        worldY += (int)(speed * Math.sqrt(2) / 2);
                        break;
                }
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
                    image = (spriteNum == 1) ? attack1up : attack2up;
                    break;
                case "down":
                    image = (spriteNum == 1) ? attack1up : attack2up;
                    break;
                case "left":
                    image = (spriteNum == 1) ? attack1up : attack2up;
                    break;
                case "right":
                    image = (spriteNum == 1) ? attack1right : attack2right;
                    break;
                case "up-right":
                    image = (spriteNum == 1) ? attack1right : attack2right;
                    break;
                case "up-left":
                    image = (spriteNum == 1) ? attack1up : attack2up;
                    break;
                case "down-right":
                    image = (spriteNum == 1) ? attack1right : attack2right;
                    break;
                case "down-left":
                    image = (spriteNum == 1) ? attack1up : attack2up;
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
    
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
    
    public void attack(){
        attacking = true;
        attackCounter = 0;
    }
    
}
