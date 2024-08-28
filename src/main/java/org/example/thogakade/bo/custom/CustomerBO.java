package org.example.thogakade.bo.custom;
import org.example.thogakade.bo.SuperBO;
import org.example.thogakade.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerDTO> getAllCustomer(Connection connection) throws SQLException;
    boolean updateCustomer(CustomerDTO customerDTO,Connection connection) throws SQLException;
    boolean saveCustomer(CustomerDTO customerDTO , Connection connection) throws SQLException;
    boolean deleteCustomer(String id,Connection connection) throws SQLException;
}
