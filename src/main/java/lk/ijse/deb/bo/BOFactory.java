package lk.ijse.deb.bo;

import lk.ijse.deb.bo.custom.impl.*;

public class BOFactory {
  private static BOFactory boFactory;
  private BOFactory() {

  }
  public static BOFactory getBoFactory() {
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }
    public enum  BOTypes{
      EMPLOYEE,BUYER,ROWMATIRIAL,SUPPLIER,PAYMENT,PLASEORDER,PRODUCT
    }
    public SuperBO getBO(BOTypes boTypes){
      switch (boTypes){
          case EMPLOYEE:
              return new EmployeeBOImpl();
          case BUYER:
              return new BuyerBOImpl();
              case ROWMATIRIAL:
                  return new RowMatirialBOImpl();
                  case SUPPLIER:
                      return new SupplierBOImpl();
          case PAYMENT:
              return new PaymentBOImpl();
              case PLASEORDER:
                  return new PlaseOrderBOImpl();
          case PRODUCT:
              return new ProductBOImpl();

          default:
              return null;
      }
    }
}
