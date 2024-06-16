package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.UtilityTool;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int collisionSpeed;
    public String name;


    public boolean attacking;
    public int attackDuration;
    public int attackCounter;

    public BufferedImage attack1up, attack2up, attack1right, attack2right;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, up_right1, up_left1, down_right1, down_left1;
    public String directions = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public Projectile projectile;
    public BufferedImage image, image2, image3;
    public boolean collision = false;

    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int actionLockerCounter = 0;
    public boolean dying = false;
    public int shotAvalivleCounter = 0;
    public boolean alive = true;
    int dyingCounter = 0;
    boolean hpBarOn = false;
    int hpBarCounter = 0;

    public int defSpeed;
    public int defAttack;
    public int defDefense;
    public int defThorns;
    public int defMaxLife;
    
    public int speedLvl;
    public int attackLvl;
    public int healthLvl;
    public int projectileLvl;

    public int health;
    public int type;
    public int speed;
    public int maxLife;
    public int life;
    public int attack;
    public int defense;
    public int thorns;
    public Entity currentWeapon;
    public Entity currentArmor;

    public int attackValue;
    public int defenseValue;
    public int thornsValue;
    public int speedValue;
    public int maxLifeValue;
    
    public boolean onPath = false;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (
        worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ){
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
            //System.out.println("Type: " + type);
            if(type == 2 && hpBarOn){

                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                //System.out.println("Life: " + life);
                hpBarCounter++;

                if(hpBarCounter > 300){
                    hpBarOn = false;
                    hpBarCounter = 0;
                }
            }
            
            if(invincible){
                hpBarOn = true;
                hpBarCounter = 0;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    public void setAction(){

    }
    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true)
        {
            if(gp.player.invincible == false){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }

        }

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
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void start(){
        
    }

    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image,gp.tileSize,gp.tileSize); 
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }

    /*public void checkCollision()
    {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        //gp.cChecker.checkObject(this,false);
        //gp.cChecker.checkEntity(this, gp.monster);
        //boolean contactPlayer = gp.cChecker.checkPlayer(this);
        //if(this.type == type_monster && contactPlayer == true)
        //{
        //    damagePlayer(attack);
        //}
    }
    public void searchPath(int goalCol, int goalRow)
    {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;
        gp.pFinder.setNodes(startCol,startRow,goalCol,goalRow,this);
        if(gp.pFinder.search() == true)
        {
            //Next WorldX and WorldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            //Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            // TOP PATH
            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize)
            {
                directions = "up";
            }
            // BOTTOM PATH
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize)
            {
                directions = "down";
            }
            // RIGHT - LEFT PATH
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize)
            {
                //either left or right
                // LEFT PATH
                if(enLeftX > nextX)
                {
                    directions = "left";
                }
                // RIGHT PATH
                if(enLeftX < nextX)
                {
                    directions = "right";
                }
            }
            //OTHER EXCEPTIONS
            else if(enTopY > nextY && enLeftX > nextX)
            {
                // up or left
                directions = "up";
                checkCollision();
                if(collisionOn == true)
                {
                    directions = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX)
            {
                // up or right
                directions = "up";
                checkCollision();
                if(collisionOn == true)
                {
                    directions = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX)
            {
                // down or left
                directions = "down";
                checkCollision();
                if(collisionOn == true)
                {
                    directions = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX)
            {
                // down or right
                directions = "down";
                checkCollision();
                if(collisionOn == true)
                {
                    directions = "right";
                }
            }
            // for following player, disable this. It should be enabled when npc walking to specified location
//            int nextCol = gp.pFinder.pathList.get(0).col;
//            int nextRow = gp.pFinder.pathList.get(0).row;
//            if(nextCol == goalCol && nextRow == goalRow)
//            {
//                onPath = false;
//            }
        }
    }*/
}
