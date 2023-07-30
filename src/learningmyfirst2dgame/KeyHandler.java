package learningmyfirst2dgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 

public class KeyHandler implements KeyListener { // inerface KeyListener para receber os eventos do teclado.

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    @Override
    public void keyTyped(KeyEvent e) {       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); // retorna o inteiro KeyCode associado com a key do evento. ex: A = 65, D = 68, etc.
        
        if (code == KeyEvent.VK_W){
            upPressed = true;
        }
        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode(); 
        
        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
    
}
