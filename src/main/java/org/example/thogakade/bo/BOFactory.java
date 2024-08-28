package org.example.thogakade.bo;


import org.example.thogakade.bo.custom.impl.CustomerBOImpl;
import org.example.thogakade.bo.custom.impl.ItemBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBOFactory(){
        return (boFactory == null)?boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM
    }

    public SuperBO BOTypes(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return (SuperBO) new CustomerBOImpl();
            case ITEM:
                return (SuperBO) new ItemBOImpl();
            default:
                return null;
        }
    }
}
