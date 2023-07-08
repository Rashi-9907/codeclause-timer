//TimerAndStopwatch


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Codeclause2 extends JFrame {
    private Timer timer;
    private int seconds;
    private JLabel timerLabel;
    private JLabel stopwatchLabel;
    private JButton startTimerButton;
    private JButton stopTimerButton;
    private JButton startStopwatchButton;
    private JButton stopStopwatchButton;
    private Thread stopwatchThread;
    private boolean stopwatchRunning;

    public Codeclause2() {
        setTitle("Timer and Stopwatch");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timerLabel = new JLabel("Timer: 0 seconds");
        stopwatchLabel = new JLabel("Stopwatch: 0 seconds");

        startTimerButton = new JButton("Start Timer");
        stopTimerButton = new JButton("Stop Timer");
        startStopwatchButton = new JButton("Start Stopwatch");
        stopStopwatchButton = new JButton("Stop Stopwatch");

        startTimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        stopTimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });

        startStopwatchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startStopwatch();
            }
        });

        stopStopwatchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopStopwatch();
            }
        });

        add(timerLabel);
        add(stopwatchLabel);
        add(startTimerButton);
        add(stopTimerButton);
        add(startStopwatchButton);
        add(stopStopwatchButton);

        setVisible(true);
    }

    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }

        seconds = 0;
        timerLabel.setText("Timer: " + seconds + " seconds");

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seconds++;
                timerLabel.setText("Timer: " + seconds + " seconds");
            }
        });

        timer.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    private void startStopwatch() {
        stopwatchRunning = true;

        stopwatchThread = new Thread(new Runnable() {
            public void run() {
                seconds = 0;
                stopwatchLabel.setText("Stopwatch: " + seconds + " seconds");

                while (stopwatchRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    seconds++;
                    stopwatchLabel.setText("Stopwatch: " + seconds + " seconds");
                }
            }
        });

        stopwatchThread.start();
    }

    private void stopStopwatch() {
        stopwatchRunning = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Codeclause2();
            }
        });
    }
}