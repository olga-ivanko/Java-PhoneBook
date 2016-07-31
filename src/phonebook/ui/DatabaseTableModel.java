/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.ui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author homefulloflove
 */
public class DatabaseTableModel extends AbstractTableModel {

    private List<String> columnNames = new ArrayList();
    private ArrayList columnTypes = new ArrayList();
    private ArrayList data = new ArrayList();
    private boolean editable;

    public DatabaseTableModel(boolean editable) {
        this.editable = editable;
    }

    @Override
    public int getRowCount() {
        synchronized (data) {
            return data.size();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        synchronized (data) {
            return ((ArrayList) data.get(row)).get(column);
        }
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        synchronized (data) {
            ((ArrayList) data.get(row)).set(column, value);
        }
    }

    public void setDataSource(ResultSet rs) throws Exception {
        data.clear();
        columnNames.clear();
        columnTypes.clear();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 0; i < columnCount; i++) {

            columnNames.add(rsmd.getColumnName(i + 1));
            Class type = Class.forName(rsmd.getColumnClassName(i + 1));
            columnTypes.add(type);

        }

        fireTableStructureChanged();
        Pattern pattern = Pattern.compile("(\\d{1})(\\d{2})(\\d{3})(\\d{2})(\\d{2})");

        while (rs.next()) {

            ArrayList row = new ArrayList();
            for (int i = 0; i < columnCount; i++) {

                String obj = String.valueOf(rs.getObject(i + 1));
                Matcher m = pattern.matcher(obj);

                if (columnTypes.get(i) == String.class) {
//                    Matcher m = pattern.matcher(rs.getString(i + 1));
//                    if (m.find()) {
//                        String newFormat = rs.getString(i + 1).format("+380 (%s) %s-%s-%s", m.group(2), m.group(3), m.group(4), m.group(5));
//                        row.add(newFormat);
//                    } else {
                    row.add(rs.getString(i + 1));
//                    }
                } else if (m.find()) {
                    String newFormat = obj.format("+380 (%s) %s-%s-%s", m.group(2), m.group(3), m.group(4), m.group(5));
                    System.out.println(newFormat);
                    row.add(newFormat);
                } else {
                    row.add(rs.getObject(i + 1));
                    System.out.println(m.find());
                }
            }
            synchronized (data) {
                data.add(row);

                fireTableRowsInserted(data.size() - 1, data.size() - 1);
            }
        }
    }

}
