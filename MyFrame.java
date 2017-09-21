package MyPingPong;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Nikolaichik on 08.08.2017.
 */
class MyFrame extends JFrame {

    private MyPanel panel;
    private JMenuItem newGameItem;
    private JMenuItem pauseItem;

    MyFrame() {
        panel = new MyPanel();
        Container pane = getContentPane();
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        newGameItem = new JMenuItem("New game");
        pauseItem = new JMenuItem("Pause");
        JMenuItem exitItem = new JMenuItem("Exit");
        gameMenu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setNewGameItem();
            }
        });
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNewGameItem();
                panel.newGameStart();
            }
        });
        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (panel.timer.isRunning() && panel.newGame == false) {
                    panel.timer.stop();
                    panel.countDownTimer.stop();
                    pauseItem.setText("Continue");
                } else {
                    panel.timer.start();
                    panel.countDownTimer.start();
                    pauseItem.setText("Pause");
                }
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gameMenu.add(newGameItem);
        gameMenu.add(pauseItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);
        menuBar.add(gameMenu);
        this.setJMenuBar(menuBar);
        pane.add(panel);
        pack();
    }
    public void setNewGameItem() {
        if (panel.newGame == true) {
            newGameItem.setEnabled(true);
        } else newGameItem.setEnabled(false);
    }
}
