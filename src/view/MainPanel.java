package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MainPanel extends MyFrame {
    private JButton buttonCrudProduct;
    private JButton buttonCrudOrder;
    private JButton buttonCrudCustomer;
    private JLabel labelTitle;
    private JPanel panelMain;
    private CustomerPanel customerPanel;

    public MainPanel() {

        buttonCrudCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() != buttonCrudCustomer) {
                    return;
                }

                customerPanel = new CustomerPanel();
                customerPanel.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        buttonCrudCustomer.setEnabled(true);
                    }
                });

                buttonCrudCustomer.setEnabled(false);
            }
        });



        this.setIcon("../icons/AppIcon.png");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.buttonCrudCustomer.setFocusable(false);
        this.buttonCrudOrder.setFocusable(false);
        this.buttonCrudProduct.setFocusable(false);
    }

    public static void main(String[] args) {
        new MainPanel();
    }
}

