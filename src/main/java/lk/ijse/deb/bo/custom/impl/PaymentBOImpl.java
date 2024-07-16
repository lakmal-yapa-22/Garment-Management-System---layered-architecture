package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.DTO.OrderDetailDTO;
import lk.ijse.deb.DTO.PaymentDTO;
import lk.ijse.deb.bo.custom.PaymentBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.OrderDetailDAO;
import lk.ijse.deb.dao.custom.PaymentDAO;
import lk.ijse.deb.dao.custom.impl.OrderDAOImpl;
import lk.ijse.deb.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.deb.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.deb.entity.OrderDetail;
import lk.ijse.deb.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);



    public OrderDetailDTO searchOrderDetail(String id) throws SQLException, ClassNotFoundException {
        OrderDetailDTO orderDetail = orderDetailDAO.search(id);
        return new OrderDetailDTO(orderDetail.getOrderId(), orderDetail.getItemCode(), orderDetail.getQty(), orderDetail.getUnitPrice(), orderDetail.getSubTotal(), orderDetail.getDiscountRate(), orderDetail.getDiscount(), orderDetail.getFinalAmount());

    }

    public String searchByOrderId(String id) throws SQLException, ClassNotFoundException {
        String status = paymentDAO.searchStatus(id);


        return status;
    }

    public boolean save(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(payment.getPayment_id(), payment.getOrder_id(), payment.getCashTenderd(), payment.getBalance(), payment.getCashier(), payment.getPaymentMethod(), payment.getPaymentStatus()));
    }

    public List<PaymentDTO> getAll() throws SQLException {
        List<Payment> payments = paymentDAO.getAll();
        List<PaymentDTO> paymentList = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentDTO paymentDTO = new PaymentDTO(payment.getPayment_id(), payment.getOrder_id(), payment.getCashTenderd(), payment.getBalance(), payment.getCashier(), payment.getPaymentMethod(), payment.getPaymentStatus());
            paymentList.add(paymentDTO);
        }
        return paymentList;
    }


    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return orderDetailDAO.getOrderId();
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.CurrentPayId();
    }
}
