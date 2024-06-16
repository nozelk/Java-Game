package main;

import entity.Entity;
import entity.GreenGoblin;
import entity.Monster;
import java.awt.Point;
import java.util.Random;

public class AssetSetter {

    GamePanel gp;
    Random rand = new Random();

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Add objects setup here
    }

    public void setMonster(int wave) {
        double randomValue;
        int randomNumberx, randomNumbery;
        int spawnX, spawnY;
        int max = gp.maxWorldCol - 2;
        int numMonsters = /*wave **/ 1;
        //int healthBoost = wave * 2;
        for (int i = 0; i < numMonsters; i++) {
            if (i < gp.monster.length) {
                do{
                    randomValue = Math.random();
                    randomNumbery = randomValue < 0.5 ? 1 : max;
                    randomNumberx = (int) (Math.random() * ((max - 1) + 1)) + 1;
                    spawnX = randomNumberx * gp.tileSize;
                    spawnY = randomNumbery * gp.tileSize;
                } while (isOccupied(spawnX, spawnY));
                gp.monster[i] = new GreenGoblin(gp);
                //gp.monster[i].maxLifeValue = gp.monster[i].maxLife + healthBoost;
                //gp.monster[i].life = gp.monster[i].maxLifeValue;
                gp.monster[i].worldX = spawnX;
                gp.monster[i].worldY = spawnY;
                //System.out.println("Monster " + (i + 1) + " spawned at (" + randomNumberx  + ", " + randomNumbery  + ")");
                //spawnMonster(i);
            }
        }
    }


    private boolean isOccupied(int x, int y) {
        Point playerPos = gp.player.getPlayerPosition();
        if (playerPos.x == x && playerPos.y == y) {
            return true;
        }

        for (Entity e : gp.monster) {
            if (e instanceof Monster) {
                Monster m = (Monster) e;
                Point monsterPos = m.getMonsterPosition();
                if (monsterPos.x == x && monsterPos.y == y) {
                    return true;
                }
            }
        }

        return false;
    }
}
