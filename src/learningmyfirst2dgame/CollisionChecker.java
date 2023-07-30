package learningmyfirst2dgame;

import learningmyfirst2dgame.Entity.Entity;


public class CollisionChecker {
       
    GamePanel gp;
    
    public CollisionChecker(GamePanel gp) { // Constructor
        this.gp = gp;
    }
    
    public void checkTile(Entity entity) { // esse metodo aparece tambem para as subclasses de Entity
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction) {
            
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;                     
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];  
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];       
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){ // SE O IF FOR TRUE, O JOGADOR COLIDE EM CIMA
                    entity.collisionOn = true;
                }
                break;                
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;                     
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];  
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];       
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){ // o jogador COLIDE EM BAIXO
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;                     
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];  
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];       
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){ // COLIDE NA ESQUERDA
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;                     
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];  
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];       
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){ // COLIDE NA DIREITA
                    entity.collisionOn = true;
                }
                break;
        }
            
    }
    
}
