package LightCycles;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LobbyPanel extends JPanel{
    
    public LobbyPanel() {

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEtchedBorder());
        setPreferredSize(new Dimension(600, 425));
        setLayout(new BorderLayout());

    }
}
