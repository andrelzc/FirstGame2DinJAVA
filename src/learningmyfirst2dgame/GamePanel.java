package learningmyfirst2dgame;

import java.awt.Color; // Cria uma cor especifica
import java.awt.Dimension; // Contém a altura e a largura de um componente em um inteiro, bem como a precisão dupla. 
import java.awt.Graphics; // uma classe que tem muitos objectos desenhados na tela
import java.awt.Graphics2D; // extend class Graphics, promove mais controle sobre geometria, coordenadas, cores e layout do texto.
import javax.swing.JPanel; // auxilia na formação do layout dos seus componentes em um frame.
import learningmyfirst2dgame.Entity.Player;
import learningmyfirst2dgame.tile.tileManager;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable { // Runnable é uma interface que obriga a implementar um método sem parâmetros que não retorna nada, esse método por acaso se chama run() e contém o código a ser efetivamente executado pela thread.
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 Tile = Bloco
    final int scale = 3; // Irá ajuda na escala da resolução do monitor. 
    
    public final int tileSize = originalTileSize * scale; // 16(bloco)x3(scale) = 48px/48px
    public final int maxScreenCol = 16; // 16 Columns = Colunas
    public final int maxScreenRow = 12; // 12 Rows = Filas
    public final int screenWidth = tileSize * maxScreenCol; // Largura da tela. 768px
    public final int screenHeight = tileSize * maxScreenRow; // Altura da tela. 576px
    
    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;   
    
    // FPS
    int FPS = 60;
       
    // SYSTEM
    tileManager tileM = new tileManager(this); // instancia a imagem e passa a class GamePanel como um parametro
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this); // GamePanel instanciada na CollisionChecker
    public AssetSetter aSetter = new AssetSetter(this); // GamePanel instanciada na AssetSetter
    public UI ui = new UI(this);
    Thread gameThread; // Thread permite que um programa possa executar várias tarefas diferentes ao mesmo tempo.
    
    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH); // instanciando a class GamePanel (com o this pq esta dentro dela) e keyH.
    public SuperObject obj[] = new SuperObject[10]; // prepara 10 slots para o obj, podendo reempregar o conteudo durante o game. *NUMERO BASEADO NA PERFORMANCE DO GAME 
       
    
    public GamePanel(){ // Constructor
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // setPreferredSize = Fala o tamanho da class (JPanel)
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Desenha todos os graficos fora da tela, depois copia todo o conteudo para a tela de uma so vez. Resumo, ajuda o game a melhorar o desempenho da renderização.
        this.addKeyListener(keyH); // adicionando o Key Input
        this.setFocusable(true); // Com ele, o GamePanel estará focado em receber as keys inputs. 
    }
    
    public void setupGame() {
        
        aSetter.setObject();
        
        playMusic(0);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this); // this faz menção a class, que no caso é GamePanel.
        gameThread.start(); // Se começar a execução, a JVM faz a chamada do metodo Run
    }

//    @Override
//    public void run() {  //GAME LOOP (SLEEP)  
//                    
//        double drawInterval = 1000000000/FPS; // 1 bilhão = 1seg em nanosegundos.
//        double nextDrawTime = System.nanoTime() + drawInterval; // nano segundos + 0.01666 seconds. O tempo no sistema sobrepondo o desenho.
//        
//        // GAME LOOP
//        while(gameThread != null) {
//            
//            // 1 UPDATE: update information such as character positions
//            update(); // é chamada quando a tela precisa ser redimensionada
//            
//            // 2 DRAW: draw the screen with the updated information
//            repaint(); // Este método é usado para chamar o método update(), que internamente que chama o método paint() para redesenhar o componente.
//        
//            try {
//                
//                double remainingTime = nextDrawTime - System.nanoTime(); // devolve o tempo restante para o prox desenho.
//                remainingTime = remainingTime / 1000000; // passando de NANO para MILI, sleep só aceita MILISEGUNDOS.
//                
//                if(remainingTime < 0){
//                    remainingTime = 0; // QUEBRA O TEMPO E POE PRA DORMIR
//                }
//                
//                Thread.sleep((long) remainingTime); // sleep para o Thread do game e nao fara nada ate q termine.
//                
//                nextDrawTime += drawInterval; // 0.01666 segundos depois sera o nextDrawTime
//                
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    
//    }
    
    @Override
    public void run(){ // GAME LOOP (DELTA)
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null) {
            
            currentTime = System.nanoTime(); // verificamos a hora atual.
            
            delta += (currentTime - lastTime) / drawInterval; // (currentTime - lastTime) --> calcula quanto tempo foi passado e divide por 0.01666
            timer += (currentTime - lastTime); // a cada loop é adicionado o tempo passado no timer.
            lastTime = currentTime; // hora atual se converte em hora passada.
            
            if(delta >= 1) { // 1 é igual ao drawInterval, a cada loop somado o tempo é dividido pelo drawInterval a delta, e quando o delta alcança o drawInterval, ele é reiniciado(repaint)
                update();
                repaint();
                delta--;
                drawCount++; // a cada update e repaint, ele aumenta em 1.
            }
            
            if(timer >= 1000000000) { // timer maior ou igual 1seg em nano segundos.
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update() {
        
        player.update(); // chamando update la do Player
    }
    
    public void paintComponent(Graphics g) { // Este método é necessário para desenhar algo no JPanel, além de desenhar a cor de fundo       
        super.paintComponent(g); // Super por causa que GamePanel extende JPanel        
        Graphics2D g2 = (Graphics2D)g; // Mudou do graphics g pro 2D.

        // DEBUG
        long drawStart = 0;

        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // TILE
        tileM.draw(g2); // chamar antes do player.draw, pra nao aparecer em cima do boneco, pq é o background
        
        // OBJECT
        for (int i = 0; i < obj.length; i++) { // DIRÁ QUAL TIPO DE OBJETO IRA DESENHAR, ESCANEARA O SUPEROBJECT 1 POR 1
            if(obj[i] != null) { // COMPROVA SE TEM REALMENTE ALGUM ITEM DENTRO DO OBJ
                obj[i].draw(g2, this); // chamando metodo draw e o GAMEPANEL
            }
        }
        
        // PLAYER
        player.draw(g2); // chamando draw do player com o graphics 2d.
        
        // UI
        ui.draw(g2);

        // DEBUG
        if(keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose(); // Elimina o contexto grafico e libera recursos do sistema que estava usando.
    }
    
    public void playMusic(int i) {
        
        music.setFile(i);
        music.play();
        music.loop();
    }
    
    public void stopMusic() {
        music.stop();
    }
    
    public void playSE(int i) { // SOUND EFFECT
        
        se.setFile(i);
        se.play();
    }
}
