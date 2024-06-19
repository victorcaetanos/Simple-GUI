package view;

import controller.CustomerController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerPanel extends MyFrame {

    private JPanel panelMain;
    private JButton buttonInsert;
    private JButton buttonDelete;
    private JButton buttonUpdate;
    private JButton buttonList;
    private JTable tableList;
    private JScrollPane scrollPanelList;
    private JLabel labelTitle;
    private JLabel labelID;
    private JLabel labelName;
    private JLabel labelPhoneNumber;
    private JTextField fieldName;
    private JTextField fieldPhoneNumber;
    private JTextField fieldIDList;

    private final CustomerController customerController;

    public CustomerPanel() {

        customerController = new CustomerController();

        setTableModel(customerController.getAllCustomers());

        buttonInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fieldName.getText();
                String phoneNumber = fieldPhoneNumber.getText();
                customerController.addCustomer(name, phoneNumber);
                fieldName.setText("");
                fieldPhoneNumber.setText("");
                setTableModel(customerController.getAllCustomers());
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = fieldIDList.getText();
                String name = fieldName.getText();
                String phoneNumber = fieldPhoneNumber.getText();
                customerController.updateCustomer(id, name, phoneNumber);
                fieldIDList.setText("");
                fieldName.setText("");
                fieldPhoneNumber.setText("");
                setTableModel(customerController.getAllCustomers());
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = fieldIDList.getText();
                customerController.deleteCustomer(id);
                fieldName.setText("");
                fieldPhoneNumber.setText("");
                fieldIDList.setText("");
                setTableModel(customerController.getAllCustomers());
            }
        });

//        buttonList.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                CustomerDAOImpl pd = new CustomerDAOImpl();
//
//                String validationResult = ValidationUtils.validatePosId(fieldIDList.getText());
//                if (!validationResult.isEmpty()) {
//                    JOptionPane.showMessageDialog(null, validationResult);
//                    return;
//                }
//
//                ResultSet rs = pd.listCustomer(Integer.parseInt(fieldIDList.getText()));
//                if (rs == null) {
//                    JOptionPane.showMessageDialog(null, "Listing Person failed!");
//                    return;
//                }
//
//                try {
//                    if (!rs.next()) return;
//                    fieldName.setText(rs.getString(2));
//                    fieldPhoneNumber.setText(rs.getString(3));
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });

        this.setIcon("../icons/AppIcon.png");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.buttonInsert.setFocusable(false);
        this.buttonDelete.setFocusable(false);
        this.buttonUpdate.setFocusable(false);
        this.buttonList.setFocusable(false);
        this.tableList.setFocusable(false);
        this.tableList.setEnabled(false);
        this.tableList.setDragEnabled(false);
    }

    public void setTableModel(ResultSet rs) {
//        tableList.setModel(DbUtils.resultSetToTableModel(rs));
        try {
            tableList.setModel(buildTableModel(rs));
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
