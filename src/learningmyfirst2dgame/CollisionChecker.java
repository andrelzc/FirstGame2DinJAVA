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
        
        switch(entity.direction) { // averigua as linhas dps colunas
            
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
    
    public int checkObject(Entity entity, boolean player) {  // metodo comprova se o jogador esta pegando qualquer objeto *FEITO COM INTERSECTS*
        
        int index = 999; 
        
        for(int i = 0; i < gp.obj.length; i++) { // escanea o array obj
            
            if(gp.obj[i] != null) {
                
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;               
                // Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                
                switch(entity.direction) { // COMPOVAR A DIREÇÃO DA ENTIDADE
                    
                    case "up": entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // METODO INTERSECTS DA CLASS RECTANGLE
                            if(gp.obj[i].collision == true) { // VERIFICA SE O OBJETO É SOLIDO OU NAO
                                entity.collisionOn = true; // ATIVA COLISAO
                            }
                            if(player == true) { // VERIFICA SE É PLAYER OU NAO
                                index = i; // OBTEMOS O INDEX E DEVOLVEMOS LÁ NO RETURN
                            }
                        }
                        break;
                        
                    case "down": entity.solidArea.y += entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // INTERSECTS COMPROVA AUTOMATICAMENTE SE OS RETANGULOS ESTAO COLIDINDO OU NAO.
                            if(gp.obj[i].collision == true) { 
                                entity.collisionOn = true; 
                            }
                            if(player == true) { 
                                index = i; 
                            }
                        }
                        break;
                        
                    case "left": entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // NECESSITAMOS SABER A POSICAO ATUAL DA SOLIDAREA PARA USAR O INTERSECTS
                            if(gp.obj[i].collision == true) { 
                                entity.collisionOn = true; 
                            }
                            if(player == true) { 
                                index = i; 
                            }
                        }
                        break;
                        
                    case "right": entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) { 
                           if(gp.obj[i].collision == true) { 
                                entity.collisionOn = true; 
                            }
                            if(player == true) { 
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
    
}
