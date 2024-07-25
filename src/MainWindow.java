import javax.swing.*;
import java.awt.*;

public class MainWindow {

    public static JFrame jf;
    public static final int DOT_SIZE = 20;
    public static final int WIDTH = 14*DOT_SIZE;
    public static final int HEIGTH = 18*DOT_SIZE;
    public static boolean inGame;
    public static final int WIDTH_DOP = 80;
    public static int speed;
    public static int level;

    public MainWindow() {
        speed = 250;
        level = 1;
        jf = new JFrame();
        jf.setTitle("Arkonoid");
        jf.setSize(WIDTH+17+4+WIDTH_DOP,HEIGTH+41+4);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(null);

        PlayingField pl = new PlayingField();
        TimerLabel tl = new TimerLabel();
        pl.setupDraGame(tl,MainWindow.WIDTH+4+(MainWindow.WIDTH_DOP/4),20,60,20,18);
        tl.setForeground(Color.RED);


        jf.setLocationRelativeTo(null);
        jf.setResizable(true);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
        new Window();
    }
}
