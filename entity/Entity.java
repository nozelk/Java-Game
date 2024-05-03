package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;
    public String name;

    public boolean attacking;
    public int attackDuration;
    public int attackCounter;

    public BufferedImage attack1up, attack2up, attack1right, attack2right;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String directions;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;

}
