import java.awt.*;

public class Window {

    public Window() {
        MainWindow.inGame = true;
        PlayingField pl = new PlayingField();

        DrawGame dg = new DrawGame();
        pl.setupDraGame(dg,2,4,MainWindow.WIDTH+2,MainWindow.HEIGTH+4,16);

        GameField gf = new GameField();
        pl.setupDraGame(gf,2,4,MainWindow.WIDTH,MainWindow.HEIGTH,16);

        BallGame bl = new BallGame();
        pl.setupDraGame(bl,2,4,MainWindow.WIDTH,MainWindow.HEIGTH,16);

//        TimerLabel tl = new TimerLabel();
//        pl.setupDraGame(tl,MainWindow.WIDTH+4+(MainWindow.WIDTH_DOP/4),20,60,20,18);
//        tl.setForeground(Color.RED);

        pl.setupDraGame(bl.l1, MainWindow.WIDTH+4+(MainWindow.WIDTH_DOP/8),50,70,20,16);

        pl.setupDraGame(bl.l2, MainWindow.WIDTH+4+(MainWindow.WIDTH_DOP/2),80,70,20,16);

        pl.setupDraGame(bl.l3, MainWindow.WIDTH+4+(MainWindow.WIDTH_DOP/4),110,70,20,16);

        pl.setupDraGame(bl.l4,MainWindow.WIDTH+4+(MainWindow.WIDTH_DOP/2),140,70,20,16);

        MainWindow.jf.addKeyListener(new Game.GameEvent());
        MainWindow.jf.add(new Game());
    }
}
