package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.DTO.EmployeeDTO;
import lk.ijse.deb.DTO.RowMatirialDTO;
import lk.ijse.deb.DTO.SupplierDTO;
import lk.ijse.deb.bo.custom.RowMatirialBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.RowMatirialDAO;
import lk.ijse.deb.dao.custom.impl.RowMatirialDAOImpl;
import lk.ijse.deb.entity.Employee;
import lk.ijse.deb.entity.RowMatirial;
import lk.ijse.deb.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowMatirialBOImpl implements RowMatirialBO {
    RowMatirialDAO rowMatirialDAO = (RowMatirialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROWMATIRIAL);

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return rowMatirialDAO.delete(id);
    }

    public boolean save(RowMatirialDTO rowMatirial) throws SQLException, ClassNotFoundException {
        return rowMatirialDAO.save(new RowMatirial(rowMatirial.getInvoiceNumber(), rowMatirial.getLocation(), rowMatirial.getDescription(), rowMatirial.getQty()));
    }

    public boolean update(RowMatirialDTO rowMatirial) throws SQLException, ClassNotFoundException {
        return rowMatirialDAO.update(new RowMatirial(rowMatirial.getInvoiceNumber(),rowMatirial.getLocation(),rowMatirial.getDescription(),rowMatirial.getQty()));
    }


    public List<RowMatirialDTO> getAll() throws SQLException, ClassNotFoundException {
        List<RowMatirial> rowMatirials = rowMatirialDAO.getAll();
        List<RowMatirialDTO> rowMatirialList = new ArrayList<>();

        for (RowMatirial rowMatirial : rowMatirials){
            RowMatirialDTO rowMatirialDTO = new RowMatirialDTO(rowMatirial.getInvoiceNumber(),rowMatirial.getLocation(),rowMatirial.getDescription(),rowMatirial.getQty());
            rowMatirialList.add(rowMatirialDTO);
        }
        return rowMatirialList;
    }

    @Override
    public RowMatirial searchById(String id) throws SQLException, ClassNotFoundException {
        RowMatirial rowMatirial=  rowMatirialDAO.search(id);
        return new RowMatirialDTO(rowMatirial.getInvoiceNumber(), rowMatirial.getLocation(),rowMatirial.getDescription(), rowMatirial.getQty());
    }
}
