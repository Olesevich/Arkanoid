import javax.swing.*;
import java.text.DecimalFormat;
import java.util.TimerTask;
import java.util.Timer;

public class TimerLabel extends JLabel
{
    private DecimalFormat decimalFormat = new DecimalFormat("00");
    private Timer timer = new Timer();
    private static TimerTask timerTask;
    public static int t;//время игрока на прохождение
    private volatile int time = -1;

    public TimerLabel ()
    {
        restartTimer();
    }

    public void restartTimer()
    {
        timerTask = new TimerTask() {
            //            private volatile int time = -1;//таймер сбрасывает на ноль, если находиться тут
            @Override
            public void run() {
                time++;
                SwingUtilities.invokeLater(new Runnable()
                {
                    //                    @Override
                    public void run(){
                        t = time;
                        TimerLabel.this.setText(decimalFormat.format(t / 60) + ":" + decimalFormat.format(t % 60));
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    public static void stopTimer()
    {
        if (timerTask != null)
            timerTask.cancel();
    }

}
