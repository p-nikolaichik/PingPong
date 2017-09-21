package MyPingPong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import MyPingPong.MyFrame;

/**
 * Created by Nikolaichik on 08.08.2017.
 */
class MyPanel extends JPanel implements ActionListener {

    Timer timer = new Timer(0, this);
    private JPanel totalPointsPanel;
    private JPanel leftPointPanel;
    private JPanel rightPointPanel;
    private JLabel leftPlayerPoint;
    private JLabel rightPlayerPoint;
    private JLabel display;
    private JLabel countdown;
    private int xBall;
    private int yBall;
    private int xStep;
    private int yStep;
    private int left_dY;
    private int right_dY;
    private int leftRocketX, leftRocketY, rightRocketX, rightRocketY;
    protected boolean start;
    protected boolean newGame;
    private static int left_Point;
    private static int right_Point;
    protected Timer countDownTimer;
    private int count;
    MyPanel() {
        start = false;
        newGame = true;
        xBall = 300;
        yBall = 220;
        xStep = 1;
        yStep = 1;
        left_dY = 0;
        right_dY = 0;
        leftRocketX = 90;
        leftRocketY = 210;
        rightRocketX = 570;
        rightRocketY = 210;
        left_Point = 0;
        right_Point = 0;
        display = new JLabel("Press button \"N\" to start new game");
        display.setHorizontalAlignment(JLabel.CENTER);
        countdown = new JLabel();
        countdown.setHorizontalAlignment(JLabel.CENTER);
        Font font = new Font("Arial", Font.BOLD, 20);
        Font fontCountDown = new Font("Arial", Font.BOLD, 40);
        display.setFont(font);
        countdown.setFont(fontCountDown);
        display.setForeground(Color.WHITE);
        countdown.setForeground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.SOUTH, display);
        this.add(BorderLayout.CENTER, countdown);
        this.addKeyListener(new PlayersKeyListener());
        leftPlayerPoint = new JLabel("Player 1 points: " + " " + left_Point);
        rightPlayerPoint = new JLabel("Player 2 points: " + " " + right_Point);
        leftPlayerPoint.setForeground(Color.WHITE);
        rightPlayerPoint.setForeground(Color.WHITE);
        totalPointsPanel = new JPanel();
        leftPointPanel = new JPanel();
        leftPointPanel.setBackground(Color.RED);
        leftPointPanel.add(leftPlayerPoint);
        rightPointPanel = new JPanel();
        rightPointPanel.setBackground(new Color(0x0060EB));
        rightPointPanel.add(rightPlayerPoint);
        leftPlayerPoint.setHorizontalAlignment(SwingConstants.LEFT);
        rightPlayerPoint.setHorizontalAlignment(SwingConstants.RIGHT);
        totalPointsPanel.add(leftPointPanel);
        totalPointsPanel.add(rightPointPanel);
        totalPointsPanel.setBackground(Color.cyan);
        this.add(BorderLayout.NORTH, totalPointsPanel);
        this.setBackground(new Color(0x2F6B81));
        this.setFocusable(true);
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x3CD5FF));
        g.fillRect(20,80, 630, 340);
        g.setColor(Color.WHITE);
        g.fillRect(90,90,490, 320);
        g.setColor(new Color(0x3CD5FF));
        g.fillRect(95,95, 480,310);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(150, 160, Color.white, 600, 200, new Color(119, 2, 18));
        g2d.setPaint(gradient);
        g2d.fillOval(xBall, yBall, 40, 40);
        g.setColor(Color.RED);
        g.fillRect(leftRocketX, leftRocketY, 10, 50);
        g.setColor(Color.BLUE);
        g.fillRect(rightRocketX, rightRocketY, 10, 50);
        requestFocus();
    }
    public void ballMove() {
        xBall += xStep;
        yBall += yStep;
        if (yBall >= 365 && xBall != 100 && xBall != 530) {
            yStep = -2;
        } else if (xBall == 530 && (yBall - rightRocketY) <= 30 && (yBall - rightRocketY) >= -30) {
            xStep = -2;
        } else if (yBall <= 95  && xBall != 100 && xBall != 530) {
            yStep = 2;
        } else if (xBall == 100 && (yBall - leftRocketY) <= 30 && (yBall - leftRocketY) >= -30) {
            xStep = 2;
        }
        if (xBall == 20) {
            display.setText("Winner is Player 2!!! Press button \"N\" to start new round");
            start = false;
            newGame = true;
            right_Point++;
            rightPlayerPoint.setText("Player 2 points: " + " " + right_Point);
            if (right_Point == 10) {
                JOptionPane.showMessageDialog(null, "The Winner is Player 2", "Информация",
                        JOptionPane.WARNING_MESSAGE);
                right_Point = 0;
                left_Point = 0;
                leftPlayerPoint.setText("Player 1 points: " + " " + left_Point);
                rightPlayerPoint.setText("Player 2 points: " + " " + right_Point);
            }
            timer.stop();

        } else if (xBall == 610){
            display.setText("Winner is Player 1!!! Press button \"N\" to start new round");
            start = false;
            newGame = true;
            left_Point++;
            leftPlayerPoint.setText("Player 1 points: " + " " + left_Point);
            if (left_Point == 10) {
                JOptionPane.showMessageDialog(null, "The Winner is Player 1", "Информация",
                        JOptionPane.WARNING_MESSAGE);
                right_Point = 0;
                left_Point = 0;
                leftPlayerPoint.setText("Player 1 points: " + " " + left_Point);
                rightPlayerPoint.setText("Player 2 points: " + " " + right_Point);
            }
            timer.stop();
        }
        if (xBall <= 80 && xBall >= 50 && yBall - leftRocketY <= 50 && yBall - leftRocketY >= 30) {
            yStep = 3;
        }
        if (xBall <= 80 && xBall >= 50 && yBall - leftRocketY >= -40 && yBall - leftRocketY <= -20) {
            yStep = -3;
        }
        if (xBall >= 550 && xBall <= 580 && yBall - rightRocketY <= 50 && yBall - rightRocketY>= 30) {
            yStep = 3;
        }
        if (xBall >= 550 && xBall <= 580 && yBall - rightRocketY >= -40 && yBall - rightRocketY <= -20) {
            yStep = -3;
        }

        if (xBall <= 100 && xBall > 80 && yBall - leftRocketY >= 30 && yBall - leftRocketY < 50) {
            xStep = 1;
            yStep = 2;
        }
        if (xBall <= 100 && xBall > 80 && yBall - leftRocketY >= -40 && yBall - leftRocketY < -20) {
            xStep = 1;
            yStep = -2;
        }if (xBall >= 530 && xBall < 550 && yBall - rightRocketY >= 30 && yBall - rightRocketY < 50) {
            xStep = -1;
            yStep = 2;
        }
        if (xBall >= 530 && xBall < 550 && yBall - rightRocketY >= -40 && yBall - rightRocketY < -20) {
            xStep = -1;
            yStep = -2;
        }
    }
    public void rocketsMove() {
        if (leftRocketY >= 90 && leftRocketY <= 360) leftRocketY += left_dY;
        if (leftRocketY < 90) leftRocketY = 90;
        if (leftRocketY > 360) leftRocketY = 360;
        if (rightRocketY >= 90 && rightRocketY <= 360) rightRocketY += right_dY;
        if (rightRocketY < 90) rightRocketY = 90;
        if (rightRocketY > 360) rightRocketY = 360;
    }
    public void newGameStart() {
        newGame = false;
        timer.start();
        xBall = 300;
        yBall = 220;
        xStep = 2;
        yStep = 2;
        display.setText("Game starting...");
        countDownTimer = new Timer(1000, new PlayersKeyListener());
        countDownTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (start == true) {
            ballMove();
        }
        rocketsMove();
        repaint();
        try {
            Thread.sleep(7);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private class PlayersKeyListener implements KeyListener, ActionListener{

        public void keyPressed(KeyEvent event){
            int k = event.getKeyCode();
            if (k == KeyEvent.VK_N && start == false && newGame == true) {
                newGameStart();
            }
            int d = 3;
            if (k == KeyEvent.VK_W) left_dY = -d;
            if (k == KeyEvent.VK_S) left_dY = d;
            if (k == KeyEvent.VK_UP) right_dY = -d;
            if (k == KeyEvent.VK_DOWN) right_dY = d;
        }
        public void keyReleased(KeyEvent event) {
            System.out.println("Released = " + event.getKeyCode());
            int key = event.getKeyCode();
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) left_dY = 0;
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) right_dY = 0;
        }
        public void keyTyped(KeyEvent event) {
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ++count;
            countdown.setText(4 - count + "");
            if (count == 4) {
                countDownTimer.stop();
                countdown.setText("");
                display.setText("Game has been started");
                start = true;
                count = 0;
            }
        }
    }
}
