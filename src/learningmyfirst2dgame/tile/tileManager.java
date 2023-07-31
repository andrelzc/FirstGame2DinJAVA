package learningmyfirst2dgame.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import learningmyfirst2dgame.GamePanel;
import learningmyfirst2dgame.UtilityTool;

public class tileManager {
    
    GamePanel gp; // add clas gamepanel
    public Tile[] tile; // add class Tile como arranjo
    public int mapTileNum[][];
    
    public tileManager(GamePanel gp) { // Constructor
        
        this.gp = gp;
        
        tile = new Tile[10]; // cria 10 blocos de agua, pedra, terra, etc para o background
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow]; // instanciando uma matriz para column e row
        
        getTileImage();
        loadMap("/maps/world01.txt"); // caminho do mapa 
        
    }
    
    public void getTileImage() {

            setup(0,"grass",false);
            setup(1,"wall",true);
            setup(2,"water",true);
            setup(3,"earth",false);
            setup(4,"tree",true);
            setup(5,"sand",false);
    }

    public void setup(int index, String imageName, boolean collision) {  // INSTANCIANDO/IMPORTANDO/ESCALA E COLISAO DAS
                                                                         // IMAGENS DO getTileImage

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void loadMap(String filePath) { // parametro para auxiliar na criação de diferentes mapas
        
        try {
            
            InputStream is = getClass().getResourceAsStream(filePath); // inputStream importa o arquivo maps
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // BufferedReader irá ler esse arquivo
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine(); // readLine lê uma linha do texto
                
                while(col < gp.maxWorldCol){
                                            //.split() --> separa uma string de um espaço
                    String numbers[] = line.split(" "); // Separa a string ao redor das correspondencias guiando regularmente a expressao                   
                    int num = Integer.parseInt(numbers[col]); // convertendo de string para inteiro
                    
                    mapTileNum[col][row] = num;
                    col++;                  
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            
            br.close(); // usa pra fechar o escritor
            
        } catch(Exception e) {
            
        }            
    }
    
    public void draw(Graphics2D g2) { // CAMERA DO JOGADOR
        
        // Automating tile drawing process
        
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){ 
            
            int tileNum = mapTileNum[worldCol][worldRow]; // Os dados do mapa estao dentro do mapTileNum[][]
            
            int worldX = worldCol * gp.tileSize; // 0 * 48
            int worldY = worldRow * gp.tileSize; // 0 * 48
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if(worldX  + gp.tileSize > gp.player.worldX - gp.player.screenX && 
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&  
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) { 
               // CRIA UM LIMITE DO CENTRO DA TELA, TIPO PLANO CARTESIANO ( -X, +X, -Y, +Y ) 
               // *** O PROGRAMA ATUALMENTE SÓ DESENHA AO REDOR DO JOGADOR ***
            
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            
            worldCol++;
                
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
            
        }
    }
}
