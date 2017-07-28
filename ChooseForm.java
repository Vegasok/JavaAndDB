package javaandbd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by P.Xorygij on 26.07.17.
 */
public class ChooseForm {
    JFrame chooseForm;
    JPanel panel;
    ChooseForm(){}

    public void go(){
        chooseForm = new JFrame("Select variant");
        panel = new JPanel();

        chooseForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);

        JButton addBtn = new JButton("Add new data");
        addBtn.setBounds(75,90,120,30);
        addBtn.addActionListener(new AddBtnListener());

        JButton reqBtn = new JButton("Request data");
        reqBtn.setBounds(75,130,120,30);
        reqBtn.addActionListener(new ReqBtnListener());

        panel.add(addBtn);
        panel.add(reqBtn);

        chooseForm.add(panel);
        chooseForm.pack();
        chooseForm.setLocationRelativeTo(null);
        chooseForm.setResizable(false);
        chooseForm.setSize(270, 300);
        chooseForm.setVisible(true);
    }

    public class AddBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            AddDataForm gui = new AddDataForm();
            gui.go();
            chooseForm.dispose();
        }
    }

    public class ReqBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            GetDataForm gui = new GetDataForm();
            gui.go();
            chooseForm.dispose();
        }
    }

}
