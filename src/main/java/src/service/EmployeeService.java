package src.service;

import src.entity.Employee;
import src.error.EmployeeNotFoundException;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface EmployeeService {

    public Employee fetchEmployeeById(Long empID) throws EmployeeNotFoundException;

    public Employee saveEmployee(Employee employee);
    public  List<Employee> fetchEmployeeList() throws Exception;

    public void deleteEmployeeByID(Long empID);

    public   Employee updateEmployee(Long empID, Employee employee);

    public  Employee fetchEmployeeByName(String empName);

    public String generateEmployeeXML() throws JAXBException, FileNotFoundException;
}
