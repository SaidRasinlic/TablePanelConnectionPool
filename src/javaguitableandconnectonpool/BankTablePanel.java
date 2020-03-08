package javaguitableandconnectonpool;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.html.CSS;

public class BankTablePanel extends JPanel {
  static final String[] COLUMN_NAMES = {"id", "name", "surname", "balance", "address","gender","birthday"};
    public BankTablePanel() {
        super(new GridLayout(1, 0));
        try {
            BankDao bankDao = new BankDao("jdbc:mysql://localhost/banksystemdata", "said", "Threethree");
              List<Bank> customers = bankDao.getAll();
         DefaultTableModel table = new DefaultTableModel();
            Object [] columnNames = new Object[7];
            columnNames[0] ="ID";
            columnNames[1] ="name";
            columnNames[2]="surname";
            columnNames[3]="balance";
            columnNames[4]="address";
            columnNames[5]="gender";
            columnNames[6]="birthday";
            
            table.setColumnIdentifiers(columnNames);
           Object[] row = new Object[7];
            for (int i = 0; i < customers.size(); i++) {

                row[0] = customers.get(i).getId();
                row[1] = customers.get(i).getName();
                row[2] = customers.get(i).getSurname();
                row[3] = customers.get(i).getBalance();
                row[4] = customers.get(i).getAddress();
                row[5] = customers.get(i).getGender();
                row[6] = customers.get(i).getBirthday();
                table.addRow(row);
            }
     
            /*Vector<String> vectorNames = bookColumnNames
                .stream()
                .collect(Collectors.toCollection(Vector::new));
        Vector<Book> vectorBooks = bookList
                .stream()
                .collect(Collectors.toCollection(Vector::new));
        JTable table1 = new JTable(vectorBooks, vectorNames);*/
            JTable etable = new JTable(table);
            etable.setPreferredScrollableViewportSize(new Dimension(500, 70));
            etable.setFillsViewportHeight(true);
            MouseAdapter mouseAdapter = new TableMouseAdapter(etable);
            etable.addMouseListener(mouseAdapter);
            JScrollPane scrollPane = new JScrollPane(etable);
            add(scrollPane);
        } catch (SQLException ex) {
            System.err.print(ex.getLocalizedMessage());

        }
    }

    private class TableMouseAdapter extends MouseAdapter {

        private final JTable table;

        public TableMouseAdapter(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int numRows = table.getRowCount();
            int numCols = table.getColumnCount();
            TableModel tableModel = table.getModel();
            System.out.println("Vrijednosti podataka u tabeli:");
            for (int rowNumber = 0; rowNumber < numRows; rowNumber++) {
                System.out.print(" red " + rowNumber + ":");
                for (int columnNumber = 0; columnNumber < numCols; columnNumber++) {
                    System.out.print(" " + tableModel.getValueAt(rowNumber, columnNumber));
                }
                System.out.println();
            }
            System.out.println("------------------------");
        }

    }

}
