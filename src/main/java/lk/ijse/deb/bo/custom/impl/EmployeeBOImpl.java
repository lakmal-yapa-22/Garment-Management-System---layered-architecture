package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.DTO.EmployeeDTO;
import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.bo.custom.EmployeeBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.EmployeeDAO;
import lk.ijse.deb.dao.custom.ProductDAO;
import lk.ijse.deb.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.deb.dao.custom.impl.ProductDAOImpl;
import lk.ijse.deb.entity.Employee;
import lk.ijse.deb.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO= (EmployeeDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
  ProductDAO productDAO= (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    public boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employee.getId(),employee.getName(),employee.getContactNumber(),employee.getSalary(),employee.getAddress(),employee.getBirthday(),employee.getProductId()));
    }

    public boolean save(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employee.getId(),employee.getName(),employee.getContactNumber(),employee.getSalary(),employee.getAddress(),employee.getBirthday(),employee.getProductId()));
    }

    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeeDAO.getAll();
        List<EmployeeDTO> empList = new ArrayList<>();

        for (Employee employee : employees){
            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),employee.getName(),employee.getContactNumber(),employee.getSalary(),employee.getAddress(),employee.getBirthday(),employee.getProductId());
            empList.add(employeeDTO);
        }
        return empList;
    }


  @Override
  public ProductDTO searchId(String code) throws SQLException, ClassNotFoundException {
      // Retrieve the Product from the DAO layer
      Product product = productDAO.searchId(code);

      if (product != null) {
          // If product is found, create and return a ProductDTO
          return new ProductDTO(
                  product.getId(),
                  product.getQty(),
                  product.getPrice(),
                  product.getType(),
                  product.getColor(),
                  product.getSize()
          );
      } else {

          return null;
      }
  }

    @Override
    public ArrayList<ProductDTO> getProductCode() throws SQLException, ClassNotFoundException {
        List<Product> productEntityData =productDAO.getAll();
        ArrayList<ProductDTO> convertToDto= new ArrayList<>();
        for (Product p : productEntityData) {
            convertToDto.add(new ProductDTO(p.getId(),p.getQty(),p.getPrice(),p.getType(),p.getColor(),p.getSize()));
        }
        return convertToDto;
    }

    @Override
    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException {
      Employee employee=  employeeDAO.search(id);
      return new EmployeeDTO(employee.getId(), employee.getName(), employee.getContactNumber(), employee.getSalary(), employee.getAddress(), employee.getBirthday(),  employee.getProductId());
    }

}
