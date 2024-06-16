package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import object.OBJ_BigStrike;
import object.*;
public class Neil extends Player {

    public Neil(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
        setDefaultValues();
        getPlayerImage();

        projectile = new OBJ_BigStrike(gp);
    }

    @Override
    public void setDefaultValues() {
        super.setDefaultValues();




        health = 20;
        type = 1;
        defAttack = 1;
        defDefense = 0;
        defMaxLife = 2;
        defSpeed = 5;
        defThorns = 0;

        currentWeapon = new OBJ_Weapon_Normal(gp);
        currentArmor = new OBJ_Armor_Normal(gp);
        speed = getSpeed();
        thorns = getThorns();
        maxLife = getMaxLife();
        life = maxLife;
        attack = getAttack();
        defense = getDefense();

    }

    @Override
    public void getPlayerImage(){
        try {
            attack1up = ImageIO.read(getClass().getResourceAsStream("/res/neil/Down1Strike.PNG"));
            attack2up = ImageIO.read(getClass().getResourceAsStream("/res/neil/Down2Strike.PNG"));
            attack1right = ImageIO.read(getClass().getResourceAsStream("/res/neil/Right1Strike.PNG"));
            attack2right = ImageIO.read(getClass().getResourceAsStream("/res/neil/Right2Strike.PNG"));
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/neil/Down1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/neil/Down2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/neil/Down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/neil/Down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/neil/Down1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/neil/Down2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/neil/Right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/neil/Right2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
