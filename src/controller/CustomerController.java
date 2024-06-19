package controller;

import dao.Customer;
import dao.CustomerDAOImpl;
import utils.ValidationUtils;

import javax.swing.*;
import java.sql.ResultSet;

public class CustomerController {
    private final CustomerDAOImpl customerDAOImp;

    public CustomerController() {
        customerDAOImp = new CustomerDAOImpl();
    }

    public void addCustomer(String name, String phoneNumber) {

        String validationResult = ValidationUtils.validateEmptyParams(name, phoneNumber);
        if (!validationResult.isEmpty()) {
            JOptionPane.showMessageDialog(null, validationResult);
            return;
        }

        Customer person = new Customer(name, phoneNumber);
        if (!customerDAOImp.insertCustomer(person)) {
            JOptionPane.showMessageDialog(null, "Inserting customer failed!");
        }
    }


    public void updateCustomer(String idText, String name, String phoneNumber) {

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a numeric value.");
            return;
        }

        String validationResult = ValidationUtils.validateEmptyParamsNPosId(id, name, phoneNumber);
        if (!validationResult.isEmpty()) {
            JOptionPane.showMessageDialog(null, validationResult);
            return;
        }


        Customer person = new Customer(id, name, phoneNumber);
        if (!customerDAOImp.updateCustomer(person)) {
            JOptionPane.showMessageDialog(null, "Updating customer failed!");
        }
    }

    public void deleteCustomer(String idText) {

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a numeric value.");
            return;
        }

        String validationResult = ValidationUtils.validatePosId(id);
        if (!validationResult.isEmpty()) {
            JOptionPane.showMessageDialog(null, validationResult);
            return;
        }

        if (!customerDAOImp.deleteCustomer(id)) {
            JOptionPane.showMessageDialog(null, "Deleting customer failed!");
        }
    }

    public ResultSet getAllCustomers() {
        ResultSet rs = customerDAOImp.listAllCustomers();
        if (rs == null) {
            JOptionPane.showMessageDialog(null, "Listing all customers failed!");
        }
        return rs;
    }
}

