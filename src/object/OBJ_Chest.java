package object;

import learningmyfirst2dgame.GamePanel;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {

    GamePanel gp;
    
    public OBJ_Chest(GamePanel gp) {

        this.gp = gp;
        
        name = "Chest";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
                
            } catch(IOException e) {
                e.printStackTrace();
            }
    }
    
}
