package javaandbd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by P.Xorygij on 26.07.17.
 */
public class GetDataForm {
    JFrame getDataForm;
    JPanel panel;
    JComboBox<String> playerNamelist;
    JTextField age, club, number, position;
    JLabel ageLabel, clubLabel, numberLabel, positionLabel, summaryLabel;
    JTextArea summary;

    public static void main(String[] args) {
        GetDataForm gd = new GetDataForm();
        gd.go();
    }

    public String[] getAllNames(){
        ArrayList<String> name = new ArrayList<>();
        try {
            ConnectionToDB.Conn();
            ArrayList<Player> playerList = ConnectionToDB.getAllPlayers();
            for(Player player : playerList){
                name.add(player.getName());
            }
            //name.addAll(playerList.stream().map(Player::getName).collect(Collectors.toList()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (ConnectionToDB.conn != null ) {
                try {
                    ConnectionToDB.CloseDB();
                } catch (ClassNotFoundException | SQLException exc) {
                    exc.printStackTrace();
                }
            }
        }
        String[] arrayOfName = new String[name.size()];
        for (int i = 0; i < name.size(); i++) {
            arrayOfName[i] = name.get(i);
        }
        return arrayOfName;
    }

    public void go(){
        getAllNames();  // Need better variant
        getDataForm = new JFrame("Get data");
        panel = new JPanel();
        panel.setLayout(null);

        playerNamelist = new JComboBox<>(getAllNames());
        playerNamelist.setBounds(30,20,200,30);

        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(30,90,100,30);
        age = new JTextField();
        age.setBounds(130,90,100,30);
        age.setEditable(false);

        clubLabel = new JLabel("Club:");
        clubLabel.setBounds(30,150,100,30);
        club = new JTextField();
        club.setBounds(130,150,100,30);
        club.setEditable(false);

        numberLabel = new JLabel("Football player number:");
        numberLabel.setBounds(330,90,150,30);
        number = new JTextField();
        number.setBounds(470,90,100,30);
        number.setEditable(false);

        positionLabel = new JLabel("Football player position:");
        positionLabel.setBounds(330,150,150,30);
        position = new JTextField();
        position.setBounds(470,150,100,30);
        position.setEditable(false);

        summaryLabel = new JLabel("Information:");
        summaryLabel.setBounds(30,210,100,30);
        summary = new JTextArea();
        summary.setEditable(false);
        JScrollPane scroll = new JScrollPane(summary);
        summary.setLineWrap(true);
        scroll.setBounds(130,210,440,100);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton getDataButton = new JButton("Get info from database");
        getDataButton.setBounds(330,20,240,30);
        getDataButton.addActionListener(new GetDataListener());

        JButton goBackButton = new JButton("< Back");
        goBackButton.setBounds(130,330,100,30);
        goBackButton.addActionListener(new BackListener());

        panel.add(getDataButton);
        panel.add(goBackButton);
        panel.add(age);
        panel.add(ageLabel);
        panel.add(club);
        panel.add(clubLabel);
        panel.add(number);
        panel.add(numberLabel);
        panel.add(position);
        panel.add(positionLabel);
        panel.add(summaryLabel);
        panel.add(scroll);
        panel.add(playerNamelist);

        getDataForm.add(panel);
        getDataForm.pack();
        getDataForm.setLocationRelativeTo(null);
        getDataForm.setResizable(false);
        getDataForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getDataForm.setSize(600, 420);
        getDataForm.setVisible(true);

    }

    public class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ChooseForm gui = new ChooseForm();
            gui.go();
            getDataForm.dispose();
        }
    }

    public class GetDataListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ConnectionToDB.Conn();
                age.setText(String.valueOf(ConnectionToDB.getAge(playerNamelist.getSelectedItem().toString())));
                number.setText(String.valueOf(ConnectionToDB.getNumber(playerNamelist.getSelectedItem().toString())));
                club.setText(ConnectionToDB.getClub(playerNamelist.getSelectedItem().toString()));
                position.setText(ConnectionToDB.getPosition(playerNamelist.getSelectedItem().toString()));
                summary.setText(ConnectionToDB.getSummary(playerNamelist.getSelectedItem().toString()));

            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                if (ConnectionToDB.conn != null ) {
                    try {
                        ConnectionToDB.CloseDB();
                    } catch (ClassNotFoundException | SQLException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        }
    }
}
