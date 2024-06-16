package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Armor_Normal extends Entity{

    public OBJ_Armor_Normal(GamePanel gp) {
        super(gp);
        name = "Armor";
        down1 = setup("/res/object/rustypipe");
        
        attackValue = 0;
        defenseValue = 2;
        thornsValue = 0;
        speedValue = 0;
        maxLifeValue = 0;
    }
}
