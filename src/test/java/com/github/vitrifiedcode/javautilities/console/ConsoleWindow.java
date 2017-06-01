/*
 * Created by JFormDesigner on Fri May 19 13:05:51 CDT 2017
 */
package com.github.vitrifiedcode.javautilities.console;

import com.github.vitrifiedcode.javautilities.console.command.CommandChecker;

import javax.swing.*;

public class ConsoleWindow extends JPanel
{
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Donovan Maas
    private JScrollPane scrollPane1;
    private JTextArea console_out;
    private JTextField console_in;
    private JButton submitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public ConsoleWindow()
    {
        initComponents();
        submitButton.addActionListener(e -> submit());
        console_in.addActionListener(e -> submit());
    }

    protected void submit()
    {
        if(console_in.getText() != null && !console_out.getText().isEmpty())
        {
            CommandChecker.executeCommand(console_in.getText());
            System.out.println(console_in.getText());
            console_in.setText("");
        }
    }

    public JTextArea getConsoleOut()
    {
        return console_out;
    }

    private void initComponents()
    {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Donovan Maas
        scrollPane1 = new JScrollPane();
        console_out = new JTextArea();
        console_in = new JTextField();
        submitButton = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                                                    "", javax.swing.border.TitledBorder.CENTER,
                                                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                                                    java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent e) {if("border".equals(e.getPropertyName())) { throw new RuntimeException(); }}
        });


        //======== scrollPane1 ========
        {

            //---- console_out ----
            console_out.setEditable(false);
            scrollPane1.setViewportView(console_out);
        }

        //---- submitButton ----
        submitButton.setText("Submit");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                      .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                      .addContainerGap()
                                                                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                      .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                                                                      .addGroup(layout.createSequentialGroup()
                                                                                                      .addComponent(console_in, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                                                                                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                                      .addComponent(submitButton)))
                                                                      .addContainerGap())
                                 );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                      .addGroup(layout.createSequentialGroup()
                                      .addContainerGap()
                                      .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                      .addComponent(submitButton)
                                                      .addComponent(console_in, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                               );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
