package entity;

import main.GamePanel;

public class Projectile extends Entity{

    Entity user;
    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive){
        this.worldX = worldX;
        this.worldY = worldY;
        this.directions = direction;
        this.alive = alive;
        this.life = this.maxLife;

    }
    public void update(){
        //System.out.println("Projectile update");
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        if (monsterIndex != 999){
            gp.player.damageMonster(monsterIndex);
            //System.out.println("Monster hit");
        }
        switch (directions) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed;break;
            case "up-right":
                worldX += (int)(speed * Math.sqrt(2) / 2);
                worldY -= (int)(speed * Math.sqrt(2) / 2);
                break;
            case "up-left":
                worldX -= (int)(speed * Math.sqrt(2) / 2);
                worldY -= (int)(speed * Math.sqrt(2) / 2);
                break;
            case "down-right":
                worldX += (int)(speed * Math.sqrt(2) / 2);
                worldY += (int)(speed * Math.sqrt(2) / 2);
                break;
            case "down-left":
                worldX -= (int)(speed * Math.sqrt(2) / 2);
                worldY += (int)(speed * Math.sqrt(2) / 2);
                break;
        }

        

        life--;
        if(life <= 0){
            alive = false;
        }

        spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
    }
}
