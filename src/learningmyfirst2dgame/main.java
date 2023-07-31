package learningmyfirst2dgame;

import javax.swing.JFrame; // Cria a tela em que iremos desenhar, colocar botões, menus, caixas de texto e tudo mais que existem nas janelas de aplicativos.

public class main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame(); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Propriedade que permite fechar a janela. (BOTÃO X)
        window.setResizable(false); // Não pode ser redimensionada, tamanho fixo.
        window.setTitle("2D Adventure"); // Título.
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // adiciona a class GamePanel no obj window.
        
        window.pack(); // Faz a janela se ajustar preferencialmente ao tamanho e layout do subcomponente, que no caso é o GamePanel
        
        window.setLocationRelativeTo(null); // Janela sempre se apresentará no centro da tela.
        window.setVisible(true); // faz o frame aparecer na tela
        
        gamePanel.setupGame();
        gamePanel.startGameThread(); // A ordem das variáveis da janela é importante, especificamente as variáveis pack, relative e visible.
    }
    
}
