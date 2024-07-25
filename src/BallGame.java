import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallGame extends JLabel implements ActionListener {

    private int ballX;
    private int ballY;
    private Timer timer;
    private boolean wallRight_Up = false;//права стена мяч движеться вверх
    private boolean wallUp_right = false;//верх мяч движеться с право на лево
    private boolean wallLeft_down = false;//левая стена мяч движеться вниз
    private boolean wallRight_down = false;//права стена мяч движеться ввниз
    private boolean wallUp_left = false;//верх мяч движеться с лево на право
    private boolean wallLeft_up = false;//левая стена мяч движеться вверх
    private boolean wallDown_right = true;// от доски вверх и вправо
    private boolean wallDown_left = false;// от доски вверх и влево
    private boolean wallDown_Up = false;//от доски тупо вверх'
    private boolean wallUp_Down = false;// сверху вниз по прямой
    private boolean upBall = true;//движение мяча вверх
    private boolean downBall = false;//движение мяча вниз
    private int [][] castleX;
    private int [][] castleY;
    private int points;
    public JLabel l1,l2,l3,l4;


    public BallGame() {
        timerBall();
        initBallGame();
        initCastle();
        points = 0;
        l1 = new JLabel("Уровень");
        l3 = new JLabel("Очки");
        l2 = new JLabel();
        l4 = new JLabel();
    }

    public void initBallGame(){
        ballX = MainWindow.WIDTH/2;
        ballY = MainWindow.HEIGTH - MainWindow.DOT_SIZE*2;
    }

    public void initCastle(){
        castleX = new int[10][MainWindow.WIDTH/MainWindow.DOT_SIZE-4];
        castleY = new int[10][MainWindow.WIDTH/MainWindow.DOT_SIZE-4];
        for (int i = 0; i < castleX.length; i++) {
            if (i == 1 || i == 8 || i == 3 || i == 6){
                continue;
            }
            for (int j = 0; j < castleX[i].length; j++) {
                if(i == 0 || i == 9){
                    if (j == 0  || j == 2 || j == castleX.length-3 || j == castleX.length-1){
                        continue;
                    }
                }
                castleX[i][j] = MainWindow.DOT_SIZE*2 + j*MainWindow.DOT_SIZE;
                castleY[i][j] = MainWindow.DOT_SIZE + i*MainWindow.DOT_SIZE;
            }
        }
    }

    public void timerBall(){//скорость мяча, задается временим
        timer = new Timer(MainWindow.speed,this);
        timer.start();
    }

    public void moveBall(){
        if (wallRight_Up || wallDown_left){
            ballX -= MainWindow.DOT_SIZE;
            ballY -= MainWindow.DOT_SIZE;
        }
        if (wallUp_right || wallLeft_down){
            ballX += MainWindow.DOT_SIZE;
            ballY += MainWindow.DOT_SIZE;
        }
        if (wallRight_down || wallUp_left){
            ballX -= MainWindow.DOT_SIZE;
            ballY += MainWindow.DOT_SIZE;

        }
        if (wallLeft_up || wallDown_right){
            ballX += MainWindow.DOT_SIZE;
            ballY -= MainWindow.DOT_SIZE;
        }
        if (wallDown_Up){
            ballY -= MainWindow.DOT_SIZE;
        }
        if (wallUp_Down){
            ballY += MainWindow.DOT_SIZE;
        }
    }

    public void proverkaBallAboard(){
        if (ballX + MainWindow.DOT_SIZE > MainWindow.WIDTH - MainWindow.DOT_SIZE && upBall){//правая,снизу вверх
            wallDown_right = false;
            wallRight_Up = true;
            wallUp_right = false;
            wallLeft_down = false;
            wallRight_down = false;
            wallUp_left = false;
            wallLeft_up = false;
            wallDown_left = false;
            wallUp_Down = false;
            wallDown_Up = false;
        }
        if (ballX + MainWindow.DOT_SIZE > MainWindow.WIDTH - MainWindow.DOT_SIZE && downBall){//правая,сверху вниз
            wallUp_right = false;
            wallRight_down = true;
            wallDown_right = false;
            wallRight_Up = false;
            wallLeft_down = false;
            wallUp_left = false;
            wallLeft_up = false;
            wallDown_left = false;
            wallUp_Down = false;
            wallDown_Up = false;
        }
        if (ballY + MainWindow.DOT_SIZE < MainWindow.DOT_SIZE*2 && wallRight_Up){//верх, справо на лево
            downBall = true;
            upBall = false;
            wallRight_Up = false;
            wallUp_left = true;
            wallDown_right = false;
            wallUp_right = false;
            wallLeft_down = false;
            wallRight_down = false;
            wallLeft_up = false;
            wallDown_left = false;
            wallUp_Down = false;
            wallDown_Up = false;
        }
        if (ballY + MainWindow.DOT_SIZE < MainWindow.DOT_SIZE*2 && wallLeft_up){//верх, слево на право
            downBall = true;
            upBall = false;
            wallLeft_up = false;
            wallUp_right = true;
            wallDown_right = false;
            wallRight_Up = false;
            wallLeft_down = false;
            wallRight_down = false;
            wallUp_left = false;
            wallDown_left = false;
            wallUp_Down = false;
            wallDown_Up = false;
        }
        if (ballX + MainWindow.DOT_SIZE < MainWindow.DOT_SIZE*2 && downBall){//левая, сверху вниз
            wallUp_left = false;
            wallLeft_down = true;
            wallDown_right = false;
            wallRight_Up = false;
            wallUp_right = false;
            wallRight_down = false;
            wallLeft_up = false;
            wallDown_left = false;
            wallUp_Down = false;
            wallDown_Up = false;
        }
        if (ballX + MainWindow.DOT_SIZE < MainWindow.DOT_SIZE*2 && upBall){//левая, снизу вверх
            wallDown_left = false;
            wallLeft_up = true;
            wallDown_right = false;
            wallRight_Up = false;
            wallUp_right = false;
            wallLeft_down = false;
            wallRight_down = false;
            wallUp_left = false;
            wallUp_Down = false;
            wallDown_Up = false;
        }
        if (ballY == MainWindow.HEIGTH - MainWindow.DOT_SIZE*2 && (ballX == Game.board[3] || ballX == Game.board[4])){//летит от доски вправо
            upBall = true;
            downBall = false;
            wallDown_right = true;
            wallLeft_down = false;
            wallRight_Up = false;
            wallUp_right = false;
            wallRight_down = false;
            wallUp_left = false;
            wallLeft_up = false;
            wallDown_left = false;
            wallUp_Down = false;
            wallDown_Up = false;
        }
        if (ballY == MainWindow.HEIGTH - MainWindow.DOT_SIZE*2 && (ballX == Game.board[0] || ballX == Game.board[1])){//летит от доски вправо
            upBall = true;
            downBall = false;
            wallLeft_down = false;
            wallDown_left = true;
            wallDown_right = false;
            wallRight_Up = false;
            wallUp_right = false;
            wallRight_down = false;
            wallUp_left = false;
            wallLeft_up = false;
            wallUp_Down = false;
            wallDown_Up = false;
        }
        if (ballY == MainWindow.HEIGTH - MainWindow.DOT_SIZE*2 && ballX == Game.board[2]){
            upBall = true;
            downBall = false;
            wallLeft_down = false;
            wallDown_left = false;
            wallDown_right = false;
            wallRight_Up = false;
            wallUp_right = false;
            wallRight_down = false;
            wallUp_left = false;
            wallLeft_up = false;
            wallDown_Up  = true;
            wallUp_Down = false;
        }
        if(ballY + MainWindow.DOT_SIZE > MainWindow.HEIGTH){
            timer.stop();
            TimerLabel.stopTimer();
            JOptionPane.showMessageDialog(null,"Вы проиграли, по пробуйте заново");
        }
    }

    public void test(){
        System.out.println("down_right " + wallDown_right);
        System.out.println("rigth_up " + wallRight_Up);
        System.out.println("up_left " + wallUp_left);
        System.out.println("left_down " + wallLeft_down);
        System.out.println("down_left " + wallDown_left);
        System.out.println("left_up " + wallLeft_up);
        System.out.println("up_ringt " + wallUp_right);
        System.out.println("right_down " + wallRight_down);
        System.out.println("upBall " + upBall);
        System.out.println("downBall " + downBall);
    }

    public void proverkaMoveBallPosleCastle(){//доработать не было 3 и 5...
        for (int i = 0; i < castleX.length; i++) {
            for (int j = 0; j < castleX[i].length; j++) {
                if (ballX == castleX[i][j] && ballY == castleY[i][j]){
                    if (wallRight_Up){//
                        upBall = false;
                        downBall = true;
                        wallDown_right = false;
                        wallLeft_down = false;
                        wallRight_Up = false;
                        wallUp_right = false;
                        wallRight_down = false;
                        wallUp_left = true;
                        wallLeft_up = false;
                        wallDown_left = false;
                        wallUp_Down = false;
                        wallDown_Up = false;
                    }
                    else if (wallUp_right){//
                        upBall = true;
                        downBall = false;
                        wallDown_right = false;
                        wallLeft_down = false;
                        wallRight_Up = false;
                        wallUp_right = false;
                        wallRight_down = false;
                        wallUp_left = false;
                        wallLeft_up = true;
                        wallDown_left = false;
                        wallUp_Down = false;
                        wallDown_Up = false;
                    }
                    else if (wallRight_down){
                        upBall = false;
                        downBall = false;
                        wallDown_right = false;
                        wallLeft_down = false;
                        wallRight_Up = false;
                        wallUp_right = false;
                        wallRight_down = false;
                        wallUp_left = false;
                        wallLeft_up = false;
                        wallDown_left = false;
                        wallUp_Down = false;
                        wallDown_Up = false;
                        System.out.println(3);
                    }
                    else if (wallLeft_up){//
                        upBall = false;
                        downBall = true;
                        wallDown_right = false;
                        wallLeft_down = false;
                        wallRight_Up = false;
                        wallUp_right = true;
                        wallRight_down = false;
                        wallUp_left = false;
                        wallLeft_up = false;
                        wallDown_left = false;
                        wallUp_Down = false;
                        wallDown_Up = false;
                    }
                    else if (wallDown_left){
                        upBall = false;
                        downBall = false;
                        wallDown_right = false;
                        wallLeft_down = false;
                        wallRight_Up = false;
                        wallUp_right = false;
                        wallRight_down = false;
                        wallUp_left = false;
                        wallLeft_up = false;
                        wallDown_left = false;
                        wallUp_Down = false;
                        wallDown_Up = false;
                        System.out.println(5);
                    }
                    else if (wallLeft_down){//
                        upBall = true;
                        downBall = false;
                        wallDown_right = true;
                        wallLeft_down = false;
                        wallRight_Up = false;
                        wallUp_right = false;
                        wallRight_down = false;
                        wallUp_left = false;
                        wallLeft_up = false;
                        wallDown_left = false;
                        wallUp_Down = false;
                        wallDown_Up = false;
                    }
                    else if (wallUp_left){//
                        upBall = true;
                        downBall = false;
                        wallDown_right = false;
                        wallLeft_down = false;
                        wallRight_Up = true;
                        wallUp_right = false;
                        wallRight_down = false;
                        wallUp_left = false;
                        wallLeft_up = false;
                        wallDown_left = false;
                        wallUp_Down = false;
                        wallDown_Up = false;
                    }
                    else if (wallDown_right){//
                        upBall = false;
                        downBall = true;
                        wallDown_right = false;
                        wallLeft_down = false;
                        wallRight_Up = false;
                        wallUp_right = true;
                        wallRight_down = false;
                        wallUp_left = false;
                        wallLeft_up = false;
                        wallDown_left = false;
                        wallUp_Down = false;
                        wallDown_Up = false;
                    }
                    else if (wallDown_Up){
                        upBall = false;
                        downBall = true;
                        wallDown_right = false;
                        wallLeft_down = false;
                        wallRight_Up = false;
                        wallUp_right = false;
                        wallRight_down = false;
                        wallUp_left = false;
                        wallLeft_up = false;
                        wallDown_left = false;
                        wallUp_Down = true;
                        wallDown_Up = false;
                    }
                }
            }
        }
    }

    public void ballSCastle(){
        for (int i = 0; i < castleX.length; i++) {
            for (int j = 0; j < castleX[i].length; j++) {
                if (ballX == castleX[i][j] && ballY == castleY[i][j]){
                    castleX[i][j] = 10000;
                    castleY[i][j] = 10000;
                    points++;
                }
            }
        }
    }

    public void castleCreat(Graphics g){
        for (int i = 0; i < castleX.length; i++) {
            if (i == 1 || i == 8 || i == 3 || i == 6){
                continue;
            }
            if (i == 2 || i == 7){
                g.setColor(Color.pink);
            }
            else
                g.setColor(Color.GREEN);
            for (int j = 0; j < castleX[i].length; j++) {
                if(i == 0 || i == 9){
                    if (j == 0  || j == 2 || j == castleX.length-3 || j == castleX.length-1) {
                        continue;
                    }
                    if (j == 1 || j == castleX.length-2){
                        g.setColor(Color.YELLOW);
                    }
                    else
                        g.setColor(Color.cyan);
                }
                g.fillRect(castleX[i][j],castleY[i][j],MainWindow.DOT_SIZE,MainWindow.DOT_SIZE);
            }
        }
    }

    public void nextLevel(){
        if(points == 52){
             MainWindow.level++;
             timer.stop();
             l2.setVisible(false);
             l4.setVisible(false);
             ballX = 1000;
             ballY = 1000;
             MainWindow.inGame = false;
             MainWindow.speed -= 25;//нужно доделать, что бы начиналась новая игра, а так она просто тупо минусует
             new Window();
        }
        if(MainWindow.speed == 25){
            timer.stop();
            TimerLabel.stopTimer();
            JOptionPane.showMessageDialog(null,"Вы выйграли, Вы прошли игру!");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainWindow.inGame){
            moveBall();
            proverkaBallAboard();
            proverkaMoveBallPosleCastle();
            ballSCastle();
            l2.setText(String.valueOf(MainWindow.level));
            l4.setText(String.valueOf(points));
            nextLevel();//пока не работает
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.black);
        g.fillRect(ballX,ballY,MainWindow.DOT_SIZE,MainWindow.DOT_SIZE);
        castleCreat(g);
        repaint();
    }
}

