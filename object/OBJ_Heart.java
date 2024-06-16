package object;


import main.GamePanel;

import entity.Entity;

public class OBJ_Heart extends Entity{
    
    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {

            super(gp);

            name = "Heart";
            image = setup("/res/object/heart_full");
            image2 = setup("/res/object/heart_half");
            image3 = setup("/res/object/heart_blank");

        }
}
