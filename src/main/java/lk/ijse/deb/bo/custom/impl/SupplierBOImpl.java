package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.DTO.EmployeeDTO;
import lk.ijse.deb.DTO.RowMatirialDTO;
import lk.ijse.deb.DTO.SupplierDTO;
import lk.ijse.deb.bo.custom.SupplierBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.SupplierDAO;
import lk.ijse.deb.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.deb.entity.Employee;
import lk.ijse.deb.entity.RowMatirial;
import lk.ijse.deb.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }


    public boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
return supplierDAO.update(new Supplier(supplier.getId(),supplier.getName(),supplier.getContactNumber(),supplier.getEmail(),supplier.getAddress()));
    }

    public boolean save(SupplierDTO supplier) throws SQLException, ClassNotFoundException {

        return supplierDAO.save(new Supplier(supplier.getId(),supplier.getName(),supplier.getContactNumber(),supplier.getEmail(),supplier.getAddress()));
    }


    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers = supplierDAO.getAll();
        List<SupplierDTO> supplierList = new ArrayList<>();

        for (Supplier supplier : suppliers){
            SupplierDTO supplierDTO = new SupplierDTO(supplier.getId(),supplier.getName(),supplier.getContactNumber(),supplier.getEmail(),supplier.getAddress());
            supplierList.add(supplierDTO);
        }
        return supplierList;
    }

    @Override
    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier=  supplierDAO.search(id);
        return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getContactNumber(), supplier.getEmail(), supplier.getAddress());
    }
}
