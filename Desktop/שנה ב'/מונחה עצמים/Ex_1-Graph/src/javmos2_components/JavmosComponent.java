package javmos2_components;

import javmos2.GraphGUI;

public abstract class JavmosComponent {

    protected final GraphGUI gui;

    public JavmosComponent(GraphGUI gui) {
        this.gui = gui;
    }
    public abstract void draw(java.awt.Graphics2D graphics2D);
}
