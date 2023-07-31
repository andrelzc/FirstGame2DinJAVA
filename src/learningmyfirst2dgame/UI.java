package learningmyfirst2dgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat; // CLASSE USADA PARA FORMATAR EM DECIMAL
import object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    public UI(GamePanel gp) {
        this.gp = gp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40); // NOME DA FONTE / ESTILO DELA (BOLD, ITALIC, PLAIN, ETC) / TAMANHO
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    
    public void showMessage(String text) {
        
        message = text;
        messageOn = true;       
    }
    
    public void draw(Graphics2D g2) {
        
    // DESENHAR TEXTOS COM O GRAPHICS2D
        if(gameFinished == true) {
            
            g2.setFont(arial_40); 
            g2.setColor(Color.white);
            
            String text;
            int textLength;
            int x;
            int y;
            
            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // retorna o Tamanho do texto **FOI CONVERTIDO DE STRING PARA INTEGER
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);
            
            
            text = "Your Time is: "+dFormat.format(playTime)+"!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // retorna o Tamanho do texto **FOI CONVERTIDO DE STRING PARA INTEGER
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);
            
            
            g2.setFont(arial_80B); 
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); 
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);
            
            gp.gameThread = null;
            
        } else {
            
            g2.setFont(arial_40); 
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null); // X e Y sao metade do BLOCO = 24px
            g2.drawString("x "+ gp.player.hasKey, 74, 65); // drawImage e drawStrings 
            // (INDICAR A BASE DA LINHA DO TEXTO, NAO A SUPERIOR) indicam diferentes pontos
            
            //TIME
            playTime += (double) 1/60; // ADICIONA 1/60 SEGUNDOS A CADA LOOP 
            g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*11, 65); // LOCAL AONDE A VARIAVEL VAI FICAR NA TELA
              
            //MESSAGE 
            if(messageOn == true) {
            
                g2.setFont(g2.getFont().deriveFont(30F)); // MUDANDO O TAMANHO DA FONTE JA CRIADA. | DERIVEFONT PEGA O TAMANHO DA FONT+ FLOAT
                g2.drawString(message, 284, 160); // LOCAL AONDE A VARIAVEL VAI FICAR NA TELA
            
                messageCounter++;
            
                if(messageCounter > 120) { // 120 ---> SIGNIFICA FRAMES (1 FRAME = 1 SEG, ENT SAO 2 SEGUNDOS A DURAÇÃO)
                
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
        
        
    }
}
