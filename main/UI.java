package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import entity.Entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import object.*;
public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_20;
    BufferedImage heart_full, heart_half, heart_blank,sword,boots;
    public int commandNum = 0;
    public int updgradeNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_20 = new Font("Arial", Font.PLAIN, 20);


        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        try {
            sword = ImageIO.read(getClass().getResourceAsStream("/res/object/sword.png"));
            boots = ImageIO.read(getClass().getResourceAsStream("/res/object/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        


    }


    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial_20);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState){
            drawplayerLife();
        }
        if(gp.gameState == gp.pauseState){
            drawplayerLife();
            drawPauseScreen();
        }
        if(gp.gameState == gp.waveOver){
            drawWaveOverScreen();
        }
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
        }
        
    }

    public void drawplayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, gp.tileSize, gp.tileSize, null);

            x += gp.tileSize;
            i++;
        }
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, gp.tileSize, gp.tileSize, null);
                
            }
            x += gp.tileSize;
            i++;
        }
    }
    public void drawTitleScreen(){

        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        String text = "Java Game";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        g2.setColor(Color.GRAY);
        g2.drawString(text,x+5,y+5);

        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        x = gp.screenWidth / 2 - (gp.tileSize * 2)/2;
        y += gp.tileSize;
        g2.drawImage(gp.player.down1, x, y,gp.tileSize * 2, gp.tileSize * 2, null);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawWaveOverScreen() {

        // Draw black background
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
    
        // Draw "Wave over!" text
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        String text = "Wave over!";
        int x = getXforCenteredText(text);
        int y = gp.tileSize;
    
        g2.setColor(Color.GRAY);
        g2.drawString(text,x+5,y+5);
    
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
    
        // Draw player image
        x = gp.screenWidth / 2 - (gp.tileSize * 2)/2;
        y += gp.tileSize;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
    
        // Draw "Upgrade your character!" text
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        text = "Upgrade your character!";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3;
    
        g2.setColor(Color.GRAY);
        g2.drawString(text,x+5,y+5);
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
    
        // Draw upgrade options centered
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        int boxWidth = gp.tileSize * 4;
        int boxHeight = gp.tileSize * 4;
        int totalWidth = (boxWidth * 3) + (gp.tileSize * 2); // Width of three boxes and the spacing between them
        int startX = (gp.screenWidth - totalWidth) / 2; // Starting X coordinate to center the boxes
        int boxY = y + gp.tileSize * 2;
        
        // Speed Upgrade Box
        drawUpgradeBox("Speed", gp.player.speed, gp.player.speed, startX, boxY, boxWidth, boxHeight, boots);
        
        // Health Upgrade Box
        drawUpgradeBox("Health", gp.player.maxLife, gp.player.health, startX + boxWidth + gp.tileSize, boxY, boxWidth, boxHeight, heart_full);
        
        // Attack Upgrade Box
        drawUpgradeBox("Attack", gp.player.attack, gp.player.attack, startX + (boxWidth + gp.tileSize) * 2, boxY, boxWidth, boxHeight, sword);
    }
    
    public void drawUpgradeBox(String attribute, int currentValue, int currentLevel, int x, int y, int width, int height, BufferedImage image) {
    
        drawSubWindow(x, y, width, height);
        
        // Attribute Text
        g2.setColor(Color.WHITE);
        g2.drawString(attribute, x + 20, y + 30);
    
        // Image placeholder
        /*g2.setColor(Color.GRAY);
        g2.fillRect(x + width / 2 - 24, y + 40, 48, 48); // Placeholder for attribute image
        */
        g2.drawImage(image, x + width / 2 - gp.tileSize / 2, y + 40, gp.tileSize, gp.tileSize, null);
        // Current Value
        g2.setColor(Color.WHITE);
        g2.drawString("Current: " + currentValue, x + 20, y + 110);
    
        // Level
        g2.drawString("Level: " + currentLevel, x + 20, y + 140);
    
        // Draw selection indicator if this is the current selection
        if (updgradeNum == (x - (gp.screenWidth - (gp.tileSize * 4 * 3 + gp.tileSize * 2)) / 2) / (width + gp.tileSize)) {
            g2.drawString(">", x - gp.tileSize / 2, y + height / 2);
        }
    }
    
    



    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text,x,y);
    }
    public void drawCharacterScreen(){

        final int frameX = gp.tileSize * 2; 
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        g2.drawString("Life", textX,textY);
        textY += lineHeight;
        g2.drawString("Speed", textX,textY);
        textY += lineHeight;
        g2.drawString("Attack", textX,textY);
        textY += lineHeight;
        g2.drawString("Defence", textX,textY);
        textY += lineHeight;
        g2.drawString("Thorns", textX,textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX,textY);
        textY += lineHeight + 15;
        g2.drawString("Armor", textX,textY);


        int tailX = (frameX + frameWidth) - 30;
        // Reset textY
        textY = frameY + gp.tileSize;
        String value;


        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.speed);
        textX = getXforAlignToRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;


        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.thorns);
        textX = getXforAlignToRight(value,tailX);
        g2.drawString(value,textX,textY);
        textY += lineHeight;


        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize + 5, textY - 24, null);
        textY += gp.tileSize;
        if(gp.player.currentArmor!= null)
        {
            g2.drawImage(gp.player.currentArmor.down1, tailX - gp.tileSize + 5, textY - 24, null);
        }
        
    }


    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0,0,0,210);  // R,G,B, alfa(opacity)
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));    // 5 = width of outlines of graphics
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);

    }
    public int getXforAlignToRight(String text, int tailX)
    {
        int textLenght;
        textLenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); // Gets width of text.
        int x = tailX - textLenght;
        return x;
    }
}
