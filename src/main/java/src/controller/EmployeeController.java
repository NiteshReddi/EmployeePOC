package src.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.entity.Employee;
import src.error.EmployeeNotFoundException;
import src.service.EmployeeService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee){
        LOGGER.info("Inside saveEmployee of EmployeeController");
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> fetchEmployeeList() throws Exception {
        LOGGER.info("Inside fetchEmployeeList of EmployeeController");
        return employeeService.fetchEmployeeList();
    }

    @GetMapping("/employees/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") Long empID) throws EmployeeNotFoundException {
        return employeeService.fetchEmployeeById(empID);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long empID){
        employeeService.deleteEmployeeByID(empID);
        return "Employee Deleted Successfully";
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") Long empID,
                                   @RequestBody Employee employee){
        return employeeService.updateEmployee(empID, employee);
    }

    @GetMapping("/employees/name/{name}")
    public Employee fetchEmployeeByName(@PathVariable("name") String empName){
        return employeeService.fetchEmployeeByName(empName);
    }

    @GetMapping("/employeesXML")
    public String generateEmployeeXML() throws JAXBException, FileNotFoundException {
        return employeeService.generateEmployeeXML();
    }
}
