package javaandbd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by P.Xorygij on 26.07.17.
 */
public class StartForm {
    JFrame frame;
    JLabel welcomeLabel;
    JPanel panel;

    public static void main(String[] args) {
        StartForm sf = new StartForm();
        sf.go();
    }

    public void go(){
        frame = new JFrame("Football players database");
        panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);

        JButton continueBtn = new JButton("Continue");
        continueBtn.setBounds(85,165,85,30);
        continueBtn.addActionListener(new ContinueListener());

        welcomeLabel = new JLabel("Welcome to football players database!");
        welcomeLabel.setBounds(25,85,225,30);

        panel.add(welcomeLabel);
        panel.add(continueBtn);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(270, 300);
        frame.setVisible(true);
    }

    class ContinueListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            ChooseForm chf = new ChooseForm();
            chf.go();
            frame.dispose();
        }
    }
}
