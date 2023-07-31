package object;

import learningmyfirst2dgame.GamePanel;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject {

    GamePanel gp;
    
    public OBJ_Boots(GamePanel gp) { // Constructor

            this.gp = gp;

            name = "Boots";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
                
            } catch(IOException e) {
                e.printStackTrace();
            }
    }
}
