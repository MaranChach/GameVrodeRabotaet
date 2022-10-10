import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Window extends JFrame {
    public Window() {
        setTitle("помргите");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocation(0, 0);
        add(new Game());

        setIconImage(new ImageIcon("4head.png").getImage());

        setVisible(true);
    }

}


