package view;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {  
  
    public CustomTableModel() {  
        super();  
    }
    
    public CustomTableModel(Object[][] data, Object[] columnNames) {  
        super(data, columnNames);  
    }
    
    public boolean isCellEditable(int row, int column) {  
        return false;  
    }
}
