package org.example.thogakade.bo.custom.impl;

import org.example.thogakade.bo.custom.CustomerBO;
import org.example.thogakade.dao.DAOFactory;
import org.example.thogakade.dao.custom.CustomerDAO;
import org.example.thogakade.dto.CustomerDTO;
import org.example.thogakade.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().DAOTypes(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public List<CustomerDTO> getAllCustomer(Connection connection) throws SQLException {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<Customer> customerList = customerDAO.getAll(connection);
        for (Customer customers:customerList) {
            customerDTOList.add(new CustomerDTO(customers.getId(),customers.getName(),customers.getCity(),customers.getTel()));
        }
        return customerDTOList;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO , Connection connection) throws SQLException {
        return customerDAO.update(new Customer(customerDTO.getId(), customerDTO.getName(),customerDTO.getCity(), customerDTO.getTel()),connection);
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO , Connection connection) throws SQLException {
        return customerDAO.save(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getCity(), customerDTO.getTel()),connection);
    }

    @Override
    public boolean deleteCustomer(String id,Connection connection) throws SQLException {
        return customerDAO.delete(id,connection);
    }
}
