package org.example.thogakade.dao;


import org.example.thogakade.dao.custom.impl.CustomerDAOImpl;
import org.example.thogakade.dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDAOFactory(){
        return (daoFactory == null)?daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,iTEM,ORDER,ORDER_DETAIL
    }

    public SuperDAO DAOTypes(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case iTEM:
                return (SuperDAO) new ItemDAOImpl();
            default:
                return null;
        }
    }
}
