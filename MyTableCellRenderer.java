package LightCycles;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

@SuppressWarnings("serial")
public class MyTableCellRenderer extends JLabel implements TableCellRenderer {
    final Border blueBorder = BorderFactory.createLineBorder(Color.BLUE);
    final Border grayBorder = BorderFactory.createLineBorder(Color.gray);
    public MyTableCellRenderer()  {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table,
        Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        setBackground((Color) value);
        if (isSelected) {
            setBorder(blueBorder);
        } else {
            setBorder(grayBorder);
        }
        return this;
    }
}


