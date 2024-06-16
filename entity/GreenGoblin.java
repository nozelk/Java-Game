package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.CollisionChecker;
import main.GamePanel;

public class GreenGoblin extends Monster{

    public GreenGoblin(GamePanel gp) {
        super(gp);
        setDefaultValues(screenX, screenY);
        getMonsterImage();
    }

    @Override
    public void setDefaultValues(int spawnX, int spawnY) {
        super.setDefaultValues(spawnX, spawnY);
        speed = 2;
        attack = 1;
        defense = 0;
        collisionSpeed = 1;
        maxLife = 5;
        life = maxLife;
        points = 5;
        
    }

    @Override
    public void getMonsterImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/greengoblin/GoblinLeft1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/greengoblin/GoblinLeft2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/greengoblin/GoblinLeft1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/greengoblin/GoblinLeft2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/greengoblin/GoblinLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/greengoblin/GoblinLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/greengoblin/GoblinRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/greengoblin/GoblinRight2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    


}
