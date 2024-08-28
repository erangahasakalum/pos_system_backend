package org.example.thogakade.bo.custom.impl;



import org.example.thogakade.bo.custom.ItemBO;
import org.example.thogakade.dao.DAOFactory;
import org.example.thogakade.dao.custom.ItemDAO;
import org.example.thogakade.dto.ItemDTO;
import org.example.thogakade.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().DAOTypes(DAOFactory.DAOTypes.iTEM);

    @Override
    public List<ItemDTO> getAllItem(Connection connection) throws SQLException {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        List<Item> itemList = itemDAO.getAll(connection);
        for (Item item:itemList) {
            itemDTOList.add(new ItemDTO(item.getItemCode(),item.getItemName(),item.getQTYOnHand(),item.getUnitPrice()));
        }
        return itemDTOList;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO, Connection connection) throws SQLException {
        return itemDAO.update(new Item(itemDTO.getItemCode(), itemDTO.getItemName(),itemDTO.getQTYOnHand(), itemDTO.getUnitPrice()),connection);
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO, Connection connection) throws SQLException {
        return itemDAO.save(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getQTYOnHand(), itemDTO.getUnitPrice()),connection);
    }

    @Override
    public boolean deleteItem(String id, Connection connection) throws SQLException {
        return itemDAO.delete(id,connection);
    }
}
