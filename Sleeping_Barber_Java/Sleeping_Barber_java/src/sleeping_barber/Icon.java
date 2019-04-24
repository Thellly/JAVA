package sleeping_barber;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class Icon {
    private String value;
    private ImageIcon icon;
    
    public Icon(String value, ImageIcon icon) {
        this.value = value;
        this.icon = icon;
    }
  
    public String getValue() {
        return value;
    }
  
    public ImageIcon getIcon() {
        return icon;
    }
  
    public String toString() {
        return value;
    }
}

class ListEntryCellRenderer
 extends JLabel implements ListCellRenderer
{
   private JLabel label;
  
   public Component getListCellRendererComponent(JList list, Object value,
                                                 int index, boolean isSelected,
                                                 boolean cellHasFocus) {
      Icon entry = (Icon) value;
  
      setText(value.toString());
      setIcon(entry.getIcon());
   
      if (isSelected) {
         setBackground(list.getSelectionBackground());
         setForeground(list.getSelectionForeground());
      }
      else {
         setBackground(list.getBackground());
         setForeground(list.getForeground());
      }
  
      setEnabled(list.isEnabled());
      setFont(list.getFont());
      setOpaque(true);
  
      return this;
   }
}