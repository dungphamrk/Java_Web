package service.B4;

import dao.B4.EmployeeDao;
import dao.B4.EmployeeDaoImp;
import model.B4.Employee;

import java.util.List;
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeDao employeeDao;

    public EmployeeServiceImp() {
        this.employeeDao = new EmployeeDaoImp();
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public boolean save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee findById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public boolean update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public boolean delete(int id) {
        return employeeDao.delete(id);
    }
}
