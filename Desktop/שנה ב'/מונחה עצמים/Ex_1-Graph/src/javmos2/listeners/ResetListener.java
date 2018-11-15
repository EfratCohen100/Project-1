package javmos2.listeners;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javmos2.GraphGUI;
import javmos2_components.JavmosPanel;

public class ResetListener implements ActionListener {

    private final GraphGUI gui;
    private final JavmosPanel panel;

    public ResetListener(GraphGUI gui, JavmosPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gui.resetFields();
        panel.repaint();
    }
}
