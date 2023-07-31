package learningmyfirst2dgame.Entity;

import java.awt.Rectangle; // com esta classa, pode criar um retangulo invisivel ou abstrato, armazena dados x, y, weidth e height
import java.awt.image.BufferedImage;

public class Entity { // SuperClass q sera usada pra o player, NPC e monsters
    
    public int worldX, worldY; // posição do jogador no mapa global
    public int speed;    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // BufferedImage --> acessa dados de uma imagem.
    public String direction;    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
