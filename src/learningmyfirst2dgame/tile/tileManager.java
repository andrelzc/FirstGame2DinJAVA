package learningmyfirst2dgame.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import learningmyfirst2dgame.GamePanel;

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
        try {
            
            tile[0] = new Tile(); // instancia o array Tile para grass
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            
            tile[1] = new Tile(); // instancia o array Tile para o wall
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;
            
            tile[2] = new Tile(); // instancia o array Tile para a water
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;
            
            tile[3] = new Tile(); // instancia o array Tile para a water
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            
            tile[4] = new Tile(); // instancia o array Tile para a water
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;
            
            tile[5] = new Tile(); // instancia o array Tile para a water
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
            
        } catch(IOException e) {
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
            
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            
            worldCol++;
                
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
            
        }
        }
}
