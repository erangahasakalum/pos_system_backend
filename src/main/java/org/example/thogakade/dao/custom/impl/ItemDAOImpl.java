package org.example.thogakade.dao.custom.impl;
import org.example.thogakade.dao.SQLUtil;
import org.example.thogakade.dao.custom.ItemDAO;
import org.example.thogakade.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item dto , Connection connection) throws SQLException {
        return SQLUtil.executeQuery("INSERT INTO item VALUES(?,?,?,?)",connection,dto.getItemCode(),dto.getItemName(),dto.getQTYOnHand(),dto.getUnitPrice());
    }

    @Override
    public List<Item> getAll(Connection connection) throws SQLException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM item",connection);
        List<Item> itemList = new ArrayList<>();
        while (resultSet.next()){
            Item item = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
            itemList.add(item);
        }
        return itemList;
    }

    @Override
    public boolean update(Item dto, Connection connection) throws SQLException {
        return SQLUtil.executeQuery("UPDATE item SET itemName = ?, QTYOnHand = ?, unitPrice = ? WHERE itemCode = ?",connection,dto.getItemName(),dto.getQTYOnHand(),dto.getUnitPrice(),dto.getItemCode());
    }

    @Override
    public boolean delete(String id,Connection connection) throws SQLException {
        return SQLUtil.executeQuery("DELETE FROM item WHERE itemCode = ?",connection,id);
    }
}
