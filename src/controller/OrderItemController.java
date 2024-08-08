package controller;

import dao.Customer;
import dao.CustomerDAOImpl;
import utils.ParseUtils;
import utils.ValidationUtils;
import view.MyFrame;

import java.sql.ResultSet;

public class OrderItemController {
    private final CustomerDAOImpl customerDAO;
    private final MyFrame panel;

    public OrderItemController(MyFrame panel) {
        this.panel = panel;
        customerDAO = new CustomerDAOImpl();
    }

    public boolean addCustomer(String name, String phoneNumber, String email) {

        String parsedPhoneN = ParseUtils.parsePhoneNumber(phoneNumber, panel);
        if (parsedPhoneN == null) return false;

        String validationResult = ValidationUtils.validateName(name);
        if (!validationResult.isEmpty()) {
            panel.showErrorMessage(validationResult);
            return false;
        }
        validationResult = ValidationUtils.validatePhoneNumber(parsedPhoneN);
        if (!validationResult.isEmpty()) {
            panel.showErrorMessage(validationResult);
            return false;
        }
        validationResult = ValidationUtils.validateEmail(email);
        if (!validationResult.isEmpty()) {
            panel.showErrorMessage(validationResult);
            return false;
        }


        Customer customer = new Customer(name, parsedPhoneN, email);
        if (!customerDAO.insertCustomer(customer)) {
            panel.showErrorMessage("Inserting customer failed!");
            return false;
        }
        return true;
    }


    public boolean updateCustomer(final String idText, String name, String phoneNumber, String email) {

        Integer parsedID = ParseUtils.parseId(idText, panel);
        if (parsedID == null) return false;
        String parsedPhoneN = ParseUtils.parsePhoneNumber(phoneNumber, panel);
        if (parsedPhoneN == null) return false;

        String validationResult = ValidationUtils.validateId(parsedID);
        if (!validationResult.isEmpty()) {
            panel.showErrorMessage(validationResult);
            return false;
        }
        validationResult = ValidationUtils.validateName(name);
        if (!validationResult.isEmpty()) {
            panel.showErrorMessage(validationResult);
            return false;
        }
        validationResult = ValidationUtils.validatePhoneNumber(parsedPhoneN);
        if (!validationResult.isEmpty()) {
            panel.showErrorMessage(validationResult);
            return false;
        }
        validationResult = ValidationUtils.validateEmail(email);
        if (!validationResult.isEmpty()) {
            panel.showErrorMessage(validationResult);
            return false;
        }


        Customer customer = new Customer(parsedID, name, parsedPhoneN, email);
        if (!customerDAO.updateCustomer(customer)) {
            panel.showErrorMessage("Updating customer failed!");
            return false;
        }
        return true;
    }

    public void deleteCustomer(final String custormerId) {

        Integer custormerID = ParseUtils.parseId(custormerId, panel, false);
        if (custormerID == null) return;

        String validationResult = ValidationUtils.validateId(custormerID);
        if (!validationResult.isEmpty()) {
            panel.showErrorMessage(validationResult);
            return;
        }

        // TODO: check if Customer is in a order before deleting
        //  if (orderItemDAOImp.isProductInUse(productId)) {
        //      ("Cannot delete product as it is in use in one or more orders.");
        //  }
        //  instantiate a orderItemDAOImp on constructor

        if (!customerDAO.deleteCustomer(custormerID)) {
            panel.showErrorMessage("Deleting customer failed!");
        }
    }

    public ResultSet getCustomer(String customerId) {
        Integer id = ParseUtils.parseId(customerId, panel);
        ResultSet rs = customerDAO.listCustomer(id);
        if (rs == null) {
            panel.showErrorMessage("Listing customer failed!");
        }
        return rs;
    }

    public ResultSet getAllCustomers() {
        ResultSet rs = customerDAO.listAllCustomers();
        if (rs == null) {
            panel.showErrorMessage("Listing all customers failed!");
        }
        return rs;
    }
}

