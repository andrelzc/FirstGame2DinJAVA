package learningmyfirst2dgame.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import learningmyfirst2dgame.GamePanel; // importa lá no package main aonde ta o GamePanel
import learningmyfirst2dgame.KeyHandler; // importa lá no package main aonde ta o KeyHandler

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;   
    public final int screenX;
    public final int screenY;
    int hasKey = 0; // IRÁ INDICAR QUANTAS CHAVES O PLAYER TEM
    
    public Player(GamePanel gp, KeyHandler keyH){ // Constructor
        
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // subtraindo pela metade do tamanho de ambos, screenX e Y
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); //  Jogador ficará sempre no centro da camera       
        
        // SET PLAYER'S COLLISION AREA 
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x; // Registrar valores Padroes, vai mudar o valor de solidArea.x
        solidAreaDefaultY = solidArea.y; // Registrar valores Padroes, vai mudar o valor de solidArea.y
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDefaultValues();
        getPlayerImage();     
    }
    
    public void setDefaultValues () { // vai receber valores padroes do jogador
        
        
        // Set player's default positions
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down"; // qq direção 
    }
    
    public void getPlayerImage() {
        
        try { // aqui vai ser posto as imagens
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void update() {       // metodo update chamado 60x por segundo
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
        
            if(keyH.upPressed == true) {
                direction = "up";                
            } 
            else if(keyH.downPressed == true) {
                direction = "down";
            } 
            else if(keyH.leftPressed == true) {
                direction = "left";              
            } 
            else if(keyH.rightPressed == true) {
                direction = "right";                
            }
            
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this); // chama o metodo checkTile no Player
            
            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true); // chama a Class Player como entity e verifica se é true a colisao             
            pickUpObject(objIndex);
            
            // IF COLLISION IS FALSE, PLAYER CAN MOVE   
            if(collisionOn == false) {
                switch(direction){
                    
                    case "up": worldY -= speed; // Eixo Y positivo
                        break;
                        
                    case "down": worldY += speed; // Eixo Y negativo
                        break;
                        
                    case "left": worldX -= speed; // Eixo X negativo
                        break;
                        
                    case "right": worldX += speed; // Eixo X positivo
                        break;
                }
            }
        
            spriteCounter++;
            if(spriteCounter > 12) { // a foto do jogador muda a cada 15 fotos.
                if(spriteNum == 1) {
                    spriteNum = 2;
                } 
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                    spriteCounter = 0;
            }
        }
    }
    
    
    public void pickUpObject(int i){
        
        if(i != 999) { // 999 significa que nao tocamos em nenhum objeto *NUMERO ALEATORIO BONITO KKKKKKKKKKK*
            
            String objectName = gp.obj[i].name;
            
            switch(objectName) {
                
                case "Key":
                    gp.playSE(1);
                    hasKey++; // AUMENTA A QNT CHAVE
                    gp.obj[i] = null; // OBJETO DESAPARECE
                    System.out.println("Key: " + hasKey);
                    break;
                case "Door":
                    gp.playSE(4);
                    if(hasKey > 0){ // VERIFICA SE O PLAYER TEM CHAVES
                        gp.obj[i] = null; // OBJETO DESAPARECE
                        hasKey--; // DIMINUI A QNT CHAVE
                        System.out.println("Key: " + hasKey);
                    }
                    break;
                case "Boots":
                    gp.playSE(3);
                    speed += 1; // AUMENTA A VELOCIDADE DO PLAYER
                    gp.obj[i] = null; // OBJETO DESAPARECE
                    break;
            }
            
        }
    }
    
    public void draw(Graphics2D g2) {
        
//        g2.setColor(Color.white); // Escolhe a cor usada para desenhar objetos.        
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize); // Desenha um retangulo e preencimentos com a cor especificada.

        BufferedImage image = null;
        
        switch(direction) {
            
            case "up":
                if(spriteNum == 1) {  // Sprite 1 caso seja 1
                    image = up1; 
                }
                if(spriteNum == 2) { // Sprite 2 caso seja 2
                    image = up2;
                }           
                break;    
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // (bufferedimagem, x, y, width, height, imageObserver)
    }
}
