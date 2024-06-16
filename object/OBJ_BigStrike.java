package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_BigStrike extends Projectile{

    GamePanel gp;
    public OBJ_BigStrike(GamePanel gp) {
        super(gp);
        name = "BigStrike";
        
        speed = 6;
        maxLife = 80;
        life = maxLife;
        attack = 5;
        alive = false;
        getImage();
    }
    public void getImage(){
        down1 = setup("/res/neil/StrikeNeilDown");
        down2 = setup("/res/neil/StrikeNeilDown");
        right1 = setup("/res/neil/StrikeNeilRight");
        right2 = setup("/res/neil/StrikeNeilRight");
        up1 = setup("/res/neil/StrikeNeilUp");
        up2 = setup("/res/neil/StrikeNeilUp");
        left1 = setup("/res/neil/StrikeNeilLeft");
        left2 = setup("/res/neil/StrikeNeilLeft");
        down_right1 = setup("/res/neil/StrikeNeilDownRight");
    }
}
