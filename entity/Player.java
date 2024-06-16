package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;



public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;


    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);
        this.gp = gp;
        this.keyH = keyH;


        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        speedLvl = 1;
        attackLvl = 1;
        healthLvl = 1;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){

        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 11;
        directions = "down";

    }
    public int getAttack(){
        return defAttack + currentWeapon.attackValue + currentArmor.attackValue + attackLvl - 1;
    }
    public int getDefense(){
        return defDefense + currentWeapon.defenseValue + currentArmor.defenseValue;
    }
    public int getSpeed(){
        return defSpeed + currentWeapon.speedValue + currentArmor.speedValue + speedLvl - 1;
    }
    public int getThorns(){
        return defThorns + currentWeapon.thornsValue + currentArmor.thornsValue;
    }
    public int getMaxLife(){
        return defMaxLife + currentWeapon.maxLifeValue + currentArmor.maxLifeValue + healthLvl - 1;
    }
    public void getPlayerImage(){
        
    }
    @Override
    public void update(){

        if (keyH.keySpacePressed == true){
            attack();
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

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            gp.eHandler.checkEvent();

            //int objIndex = gp.cChecker.checkObject(this, true);

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
        
        if(keyH.shotKeyPressed == true && projectile.alive == false && shotAvalivleCounter == 60){

            projectile.set(worldX, worldY, directions, true);

            shotAvalivleCounter = 0;
            gp.projectileList.add(projectile);
            

        }
        

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        
        if(shotAvalivleCounter < 60){
            shotAvalivleCounter++;
        }

    }


    public void contactMonster(int i){
        if(i != 999){

            if(invincible == false){
                life -= gp.monster[i].attack;
                invincible = true;
            }
            
        }
    }

    public void damageMonster(int i){
        if(i != 999){
            if(gp.monster[i].invincible == false){
                int damage = attack - gp.monster[i].defense;
                if(damage < 0){
                    damage = 0;
                } 
                //System.out.println(gp.monster[i].life);
                gp.monster[i].life -= damage;
                //System.out.println(gp.monster[i].life);
                gp.monster[i].invincible = true;

                if(gp.monster[i].life <= 0){
                    gp.score += gp.monster[i].points;
                    System.out.println(gp.score);
                    gp.monster[i] = null;
                }
            }
        }
        //System.out.println(gp.monster[i].life);
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
    
        if(attacking) {
            // Izberite sliko napada glede na smer
            switch (directions) {
                case "up":
                    image = (spriteNum == 1) ? attack1up : attack2up;
                    attacking = false;
                    break;
                case "down":
                    image = (spriteNum == 1) ? attack1up : attack2up;
                    attacking = false;
                    break;
                case "left":
                    image = (spriteNum == 1) ? attack1up : attack2up;
                    attacking = false;
                    break;
                case "right":
                    image = (spriteNum == 1) ? attack1right : attack2right;
                    attacking = false;
                    break;
                case "up-right":
                    image = (spriteNum == 1) ? attack1right : attack2right;
                    attacking = false;
                    break;
                case "up-left":
                    image = (spriteNum == 1) ? attack1up : attack2up;
                    attacking = false;
                    break;
                case "down-right":
                    image = (spriteNum == 1) ? attack1right : attack2right;
                    attacking = false;
                    break;
                case "down-left":
                    image = (spriteNum == 1) ? attack1up : attack2up;
                    attacking = false;
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
        while(attackCounter < 60){
            attackCounter++;
        }
        attackCounter = 0;
        
    }

   
    
    public Point getPlayerPosition() {
        return new Point(worldX, worldY);
    }
    
}
