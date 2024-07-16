package lk.ijse.deb.dao;

import lk.ijse.deb.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        EMPLOYEE,BUYER,SUPPLIER,ROWMATIRIAL,PRODUCT,ORDDER,ORDERDETAIL,PAYMENT
    }
public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case BUYER:
                return new BuyerDAOImpl();
                case SUPPLIER:
                    return new SupplierDAOImpl();
                    case ROWMATIRIAL:
                        return new RowMatirialDAOImpl();
                        case PRODUCT:
                            return new ProductDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case ORDDER:
                return  new OrderDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();


            default:
                return null;
        }
}
}
