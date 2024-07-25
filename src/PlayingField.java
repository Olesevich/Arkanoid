import javax.swing.*;
import java.awt.*;

public class PlayingField extends JLabel{


    public PlayingField() {

    }

    public void setupDraGame(JLabel draw, int x, int y, int WIDTH, int HEIGTH, int a){
        draw.setBounds(x, y, WIDTH, HEIGTH);
        MainWindow.jf.add(draw);
        draw.setFont(new Font("Times New Roman", Font.BOLD, a));
        draw.setVisible(true);
    }
}

class DrawGame extends JLabel{
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.black);
        g.drawRect(0,0,MainWindow.WIDTH,MainWindow.HEIGTH);//ресует контур вокруг черный методом drawRect()
        repaint();
    }
}

