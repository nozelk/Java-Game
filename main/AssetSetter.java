package main;

import entity.GreenGoblin;
import object.OBJ_Chest;


public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        

        


    }

    public void setMonster(){
        gp.monster[0] = new GreenGoblin(gp);
        gp.monster[0].worldX = 12 * gp.tileSize;
        gp.monster[0].worldY = 10 * gp.tileSize;

        gp.monster[1] = new GreenGoblin(gp);
        gp.monster[1].worldX = 13 * gp.tileSize;
        gp.monster[1].worldY = 12 * gp.tileSize;

    }

}
