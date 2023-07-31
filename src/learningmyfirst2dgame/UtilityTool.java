package learningmyfirst2dgame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage original, int width, int height) { // ESCALONAMENTO

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics(); // cria um Graphics2D que usa pra desenhar o BufferedImage
        g2.drawImage(original, 0, 0, width, height, null); // DESENHA O tile[0].image NO scaledImage (BufferedImage)
        g2.dispose();

        return scaledImage;
    }
}
