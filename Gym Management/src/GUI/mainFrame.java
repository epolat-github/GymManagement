/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class mainFrame extends JFrame {

    private String title;
    private JPanel panel;

    public mainFrame(String title, JPanel panel) throws HeadlessException {
        this.title = title;
        this.panel = panel;

        createFrame();
        addPanel();
    }

    private void createFrame() {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    }

    private void addPanel() {
        this.add(panel);
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
