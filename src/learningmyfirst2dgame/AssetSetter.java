package learningmyfirst2dgame;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject() { // Irá instanciar obj padroes e colocalos no mapa
        
        gp.obj[0] = new OBJ_Key(gp); // OBJ_Key subclasse de SuperObject
        gp.obj[0].worldX = 23 * gp.tileSize; // coluna 23 * tileSize(48px)
        gp.obj[0].worldY = 7 * gp.tileSize; // fileira 7 * tileSize(48px)
        
        gp.obj[1] = new OBJ_Key(gp); // 2 chave
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
        
        gp.obj[2] = new OBJ_Key(gp); // 3 chave
        gp.obj[2].worldX = 37 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;
        
        gp.obj[3] = new OBJ_Door(gp); // 1 Porta
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;
        
        gp.obj[4] = new OBJ_Door(gp); // 2 Porta
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;
        
        gp.obj[5] = new OBJ_Door(gp); // 3 Porta
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;
        
        gp.obj[6] = new OBJ_Chest(gp); // 1 bau
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 8 * gp.tileSize;     
        
        gp.obj[7] = new OBJ_Boots(gp); // 1 bau
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;  
                   
    }
}
