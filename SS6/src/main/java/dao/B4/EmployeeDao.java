package dao.B4;

import model.B4.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee>  findAll();
    boolean save(Employee employee);

    Employee findById(int id);

    boolean update(Employee employee);

    boolean delete(int id);
}
