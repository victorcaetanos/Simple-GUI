package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MyFrame extends JFrame {

    /**
     * Sets this frames icon.
     * @param imagePath the path of the image to be displayed.
     */
    public void setIcon(String imagePath) {
        InputStream imgStream = CustomerPanel.class.getResourceAsStream(imagePath);
        assert imgStream != null;
        BufferedImage myImg;
        try {
            myImg = ImageIO.read(imgStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setIconImage(myImg);
    }

    /**
     * See https://stackoverflow.com/a/10625471/1694043
     */
    public static TableModel buildTableModel(final ResultSet resultSet)
            throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();

        // Column names.
        Vector<String> columnNames = new Vector<>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            columnNames.add(resultSet.getMetaData().getColumnName(columnIndex));
        }

        // Data of the table.
        Vector<Vector<Object>> dataVector = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> rowVector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowVector.add(resultSet.getObject(columnIndex));
            }
            dataVector.add(rowVector);
        }

        return new DefaultTableModel(dataVector, columnNames);
    }
}
