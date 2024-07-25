import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public  class Game extends JPanel implements ActionListener {

    public static int [] board = new int[5];
    private static boolean left = false;
    private static boolean right = false;
    private Timer timer1;
    private static boolean leftMax = false;
    private static boolean rightMax = false;


    public Game() {
        initGame();
    }

    public void initGame(){
        for (int i = 0; i < board.length; i++) {
            board [i] = MainWindow.WIDTH/2-MainWindow.DOT_SIZE*2+i*MainWindow.DOT_SIZE;
        }
        timer1 = new Timer(1,this);
        timer1.start();
    }

    public void move(){
        if (left){
            for (int i = board.length - 1; i > 0; i--) {
                board [i] = board [i-1];
            }
            board [0] -= MainWindow.DOT_SIZE;
            left = false;
            rightMax = false;
        }
        if (right){
            for (int i = 0; i < board.length - 1; i++) {
                board [i] = board [i + 1];
            }
            board [board.length-1] += MainWindow.DOT_SIZE;
            right = false;
            leftMax = false;
        }
    }

    public void proverkaBoardAbroad(){
        if (board[3] + MainWindow.DOT_SIZE > MainWindow.WIDTH - MainWindow.DOT_SIZE){
            rightMax = true;
        }
        if (board[0] < MainWindow.DOT_SIZE){
            leftMax = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainWindow.inGame){
            move();
            proverkaBoardAbroad();
        }
    }

    static class GameEvent extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right && !leftMax){
                left = true;
            }
            if(key == KeyEvent.VK_RIGHT && !left && !rightMax){
                right = true;
            }
        }
    }
}

class GameField extends JLabel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.BLUE);
        for (int i = 0; i < Game.board.length; i++) {
            g.fillRect(Game.board[i],MainWindow.HEIGTH-MainWindow.DOT_SIZE,
                    MainWindow.DOT_SIZE,MainWindow.DOT_SIZE);
        }
        repaint();
    }
}
