package javaandbd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by P.Xorygij on 26.07.17.
 */
public class AddDataForm{
    JFrame addDataForm;
    JPanel panel;
    JTextField playerName, playerNumber, playerPosition, club, age;
    JTextArea desc;
    JLabel errorMessage;


    public void go(){
        addDataForm = new JFrame("Add data");
        panel = new JPanel();
        panel.setLayout(null);

        errorMessage = new JLabel("Data is not valid!");
        errorMessage.setForeground(Color.RED);
        errorMessage.setBounds(160,0,100,30);
        errorMessage.setVisible(false);

        JButton addDataButton = new JButton("Add to database");
        addDataButton.setBounds(15,480,145,30);
        addDataButton.addActionListener(new AddDataListener());

        JButton goBack = new JButton("< Back");
        goBack.setBounds(180,480,80,30);
        goBack.addActionListener(new GoBackListener());


        JLabel labelPlayerName = new JLabel("Football player name:");
        labelPlayerName.setBounds(15,0,150,30);
        playerName = new JTextField();
        playerName.setBounds(60,30,150,25);

        JLabel labelPlayerNumber = new JLabel("Football player number:");
        labelPlayerNumber.setBounds(15,60,150,30);
        playerNumber = new JTextField();
        playerNumber.setBounds(60,90,150,25);

        JLabel labelPlayerPosition = new JLabel("Football player position:");
        labelPlayerPosition.setBounds(15,120,150,30);
        playerPosition = new JTextField();
        playerPosition.setBounds(60,150,150,25);

        JLabel labelClub = new JLabel("Club:");
        labelClub.setBounds(15,180,150,30);
        club = new JTextField();
        club.setBounds(60,210,150,25);

        JLabel labelAge = new JLabel("Age:");
        labelAge.setBounds(15,240,150,30);
        age = new JTextField();
        age.setBounds(60,270,150,25);


        JLabel labelDesc = new JLabel("Information:");
        labelDesc.setBounds(15,300,150,30);

        desc = new JTextArea("");
        JScrollPane scroll = new JScrollPane(desc);
        desc.setLineWrap(true);

        scroll.setBounds(30,330,230,100);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(errorMessage);
        panel.add(addDataButton);
        panel.add(goBack);
        panel.add(playerName);
        panel.add(labelPlayerName);
        panel.add(playerNumber);
        panel.add(labelPlayerNumber);
        panel.add(playerPosition);
        panel.add(labelPlayerPosition);
        panel.add(club);
        panel.add(labelClub);
        panel.add(age);
        panel.add(labelAge);
        panel.add(scroll);
        panel.add(labelDesc);

        addDataForm.add(panel);
        addDataForm.pack();
        addDataForm.setLocationRelativeTo(null);
        addDataForm.setResizable(false);
        addDataForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addDataForm.setSize(300, 600);
        addDataForm.setVisible(true);
    }

    public void cleanerFields(){
        playerName.setText("");
        playerNumber.setText("");
        playerPosition.setText("");
        club.setText("");
        age.setText("");
        desc.setText("");
    }

    public boolean checkingFields(){
        return !(playerName.getText().equals("") &&
                playerPosition.getText().equals("") &&
                club.getText().equals("") &&
                desc.getText().equals(""));
    }

    public class GoBackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ChooseForm gui = new ChooseForm();
            gui.go();
            addDataForm.dispose();
        }
    }

    public class AddDataListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = playerName.getText();
            int playerage = Integer.parseInt(age.getText());
            int number = Integer.parseInt(playerNumber.getText());
            String position = playerPosition.getText();
            String pclub = club.getText();
            String summary = desc.getText();

            try {
                ConnectionToDB.Conn();
                ConnectionToDB.CreateDB();
                if(checkingFields())
                    ConnectionToDB.WriteDB(name, playerage, number, position, pclub, summary);
                else errorMessage.setVisible(true);

            } catch (ClassNotFoundException | SQLException exc) {
                exc.printStackTrace();
            } finally {
                if (ConnectionToDB.conn != null ) {
                    try {
                        ConnectionToDB.CloseDB();
                    } catch (ClassNotFoundException | SQLException exc) {
                        exc.printStackTrace();
                    }
                }
                cleanerFields();
            }

        }
    }
}
