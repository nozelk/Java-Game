package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Weapon_Normal extends Entity{

    public OBJ_Weapon_Normal(GamePanel gp) {
        super(gp);
        name = "Rusty Pipe";
        down1 = setup("/res/object/rustypipe");

        attackValue = 2;
        defenseValue = 0;
        thornsValue = 0;
        speedValue = 0;
        maxLifeValue = 0;
    }
}
