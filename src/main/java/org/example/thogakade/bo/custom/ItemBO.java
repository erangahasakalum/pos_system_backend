package org.example.thogakade.bo.custom;

import org.example.thogakade.bo.SuperBO;
import org.example.thogakade.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    <ItemDTO> List<ItemDTO> getAllItem(Connection connection) throws SQLException;
    boolean updateItem(ItemDTO itemDTO, Connection connection) throws SQLException;
    boolean saveItem(ItemDTO itemDTO , Connection connection) throws SQLException;
    boolean deleteItem(String id,Connection connection) throws SQLException;
}
