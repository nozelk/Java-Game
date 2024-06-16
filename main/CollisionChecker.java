package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public boolean checkTileAtCoordinates(int x, int y, int width, int height) {
        int leftCol = x / gp.tileSize;
        int rightCol = (x + width) / gp.tileSize;
        int topRow = y / gp.tileSize;
        int bottomRow = (y + height) / gp.tileSize;
    
        int tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
        int tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
    
        return gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.directions) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "up-right":
                entityTopRow = (int)((entityTopWorldY - entity.speed * Math.sqrt(2) / 2) / gp.tileSize);
                entityRightCol = (int)((entityRightWorldX + entity.speed * Math.sqrt(2) / 2) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "up-left":
                entityTopRow = (int)((entityTopWorldY - entity.speed * Math.sqrt(2) / 2) / gp.tileSize);
                entityLeftCol = (int)((entityLeftWorldX - entity.speed * Math.sqrt(2) / 2) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down-right":
                entityBottomRow = (int)((entityBottomWorldY + entity.speed * Math.sqrt(2) / 2) / gp.tileSize);
                entityRightCol = (int)((entityRightWorldX + entity.speed * Math.sqrt(2) / 2) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down-left":
                entityBottomRow = (int)((entityBottomWorldY + entity.speed * Math.sqrt(2) / 2) / gp.tileSize);
                entityLeftCol = (int)((entityLeftWorldX - entity.speed * Math.sqrt(2) / 2) / gp.tileSize);
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }

    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){

                entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.directions) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                                
                            }
                            if (player == true){
                                index = i;
                            }
                            
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                                
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                                
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                                
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        
        }
        return index;
    }
    public int checkEntity(Entity entity, Entity[] target)
    {
        int index = 999;   // no collision returns 999;
        //Use a temporal direction when it's being knockbacked
        String direction = entity.directions;

        for(int i = 0;i < target.length; i++)
        {
            if(target[i] != null)
            {
                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get the object's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;       //entity's solid area and obj's solid area is different.
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (direction)
                {
                    case "up" :
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down" :
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left" :
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right" :
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if(entity.solidArea.intersects(target[i].solidArea))
                {
                    if(target[i] != entity) // avoid entity includes itself as a collision target
                    {
                        entity.collisionOn = true;
                        index = i;   // Non-player characters cannot pickup objects.
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX; //Reset
                entity.solidArea.y = entity.solidAreaDefaultY;

                target[i].solidArea.x = target[i].solidAreaDefaultX;     //Reset
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity)
    {
        boolean contactPlayer = false;
        // get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // get the object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;       //entity's solid area and obj's solid area is different.
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.directions)
        {
            case "up" :
                entity.solidArea.y -= entity.speed;
                break;
            case "down" :
                entity.solidArea.y += entity.speed;
                break;
            case "left" :
                entity.solidArea.x -= entity.speed;
                break;
            case "right" :
                entity.solidArea.x += entity.speed;
                break;
        }
        if(entity.solidArea.intersects(gp.player.solidArea))
        {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX; ////Reset
        entity.solidArea.y = entity.solidAreaDefaultY;

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;     ////Reset
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
