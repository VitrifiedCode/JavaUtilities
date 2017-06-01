package com.github.vitrifiedcode.javautilities.console;

import com.github.vitrifiedcode.javautilities.console.command.CommandChecker;
import com.github.vitrifiedcode.javautilities.console.command.ConsoleVar;
import com.github.vitrifiedcode.javautilities.console.command.SetVarCommand;
import com.github.vitrifiedcode.javautilities.io.IO;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintStream;

public class Console
{
    @ConsoleVar
    static String a = "Hello World!";

    public static void main(String[] args) throws Exception
    {
        Thread t = new Thread(new Runnable()
        {
            boolean running = true;

            @Override
            public void run()
            {
                JFrame jf = new JFrame("Console");
                jf.setLocationRelativeTo(null);
                jf.setSize(800, 600);
                jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                ConsoleWindow cw = new ConsoleWindow();
                jf.add(cw);
                jf.pack();
                jf.setVisible(true);
                jf.addWindowListener(new WindowListener()
                {
                    @Override
                    public void windowOpened(WindowEvent e) {}

                    @Override
                    public void windowClosing(WindowEvent e) {}

                    @Override
                    public void windowClosed(WindowEvent e) { running = false; }

                    @Override
                    public void windowIconified(WindowEvent e) {}

                    @Override
                    public void windowDeiconified(WindowEvent e) {}

                    @Override
                    public void windowActivated(WindowEvent e) {}

                    @Override
                    public void windowDeactivated(WindowEvent e) {}
                });

                PrintStream ps = new PrintStream(new TextAreaOutputStream(cw.getConsoleOut()));
                PrintStream es = new PrintStream(new TextAreaOutputStream.TextAreaErrorStream(cw.getConsoleOut()));
                System.setOut(ps);
                System.setErr(es);

                while(running)
                {
                    IO.print("\0");
                    try { Thread.sleep(100); }
                    catch(InterruptedException e) { e.printStackTrace(); }
                }
            }
        });
        CommandChecker.registerCommand(new SetVarCommand());
        t.start();
    }
}
