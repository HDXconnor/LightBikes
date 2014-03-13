package LightCycles;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

@SuppressWarnings("serial")
public class MyTableModel extends DefaultTableModel {
    Color purple = new Color(153,0,204);
    Color orange = new Color(255,153,0);
    Object data[][] = { { "Red", Color.RED }, { "Orange", orange },
            { "Purple", purple }, { "Green", Color.GREEN },
            { "Blue", Color.BLUE }, { "Magenta", Color.MAGENTA },
            { "Yellow", Color.yellow }, { "White", Color.white }
    };

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    MyTableModel() {
        setColumnIdentifiers(new String[] {"Name", "Color" });
        for (int i = 0, n = data.length; i < n; i++)
            addRow(new Object[] {  data[i][0],
                    data[i][1] });
    }
}
