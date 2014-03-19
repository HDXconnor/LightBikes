package game;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

@SuppressWarnings("serial")
public class MyTableModel extends DefaultTableModel {
    Color purple = new Color(153,0,204);
    Color orange = new Color(255,153,0);
    Color mag = new Color(255,0,153);
    Object data[][] = { { "Red", Color.RED },{ "Blue", Color.cyan },
            { "Purple", purple }, { "Green", Color.GREEN },
            { "Orange", orange }, { "Magenta", mag },
            { "Yellow", Color.yellow }
    };

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public MyTableModel() {
        setColumnIdentifiers(new String[] {"Name", "Color" });
        for (int i = 0, n = data.length; i < n; i++)
            addRow(new Object[] {  data[i][0],
                    data[i][1] });
    }
}
