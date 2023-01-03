package src.service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.controller.EmployeeController;
import src.entity.Employee;
import src.error.EmployeeNotFoundException;
import src.repository.EmployeeRepository;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.JAXBException;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    List < Employee > temp;
    private final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);

    @Override
    public Employee fetchEmployeeById(Long empID) throws EmployeeNotFoundException {
        Optional<Employee> employee =
                employeeRepository.findById(empID);

        if(!employee.isPresent()){
            throw new EmployeeNotFoundException("Employee not Present");
        }
        return employee.get();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchEmployeeList() throws JAXBException {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployeeByID(Long empID) {
        employeeRepository.deleteById(empID);
    }

    @Override
    public Employee updateEmployee(Long empID, Employee employee) {
      Employee empDB =  employeeRepository.findById(empID).get();

      if(Objects.nonNull(employee.getEmpName()) &&
       !"".equalsIgnoreCase(employee.getEmpName())) {
          empDB.setEmpName(employee.getEmpName());
      }
        if(Objects.nonNull(employee.getEmpAddress()) &&
                !"".equalsIgnoreCase(employee.getEmpAddress())) {
            empDB.setEmpAddress(employee.getEmpAddress());
        }
        if(Objects.nonNull(employee.getEmpContact()) &&
                !"".equalsIgnoreCase(employee.getEmpContact())) {
            empDB.setEmpContact(employee.getEmpContact());
        }
        if(Objects.nonNull(employee.getEmpEmail()) &&
                !"".equalsIgnoreCase(employee.getEmpEmail())) {
            empDB.setEmpEmail(employee.getEmpEmail());
        }
        return employeeRepository.save(empDB);
    }
    @Override
    public Employee fetchEmployeeByName(String empName) {
        return employeeRepository.findByEmpName(empName);

    }

    public String generateEmployeeXML() throws JAXBException, FileNotFoundException {

        temp=employeeRepository.findAll();
        XStream xstream = new XStream(new StaxDriver());
        String str = xstream.toXML(temp);

        try {

            FileWriter fw = new FileWriter("C:\\Users\\Nitesh Reddy\\OneDrive\\Desktop\\testdata.xml");

            for (int i = 0; i < str.length(); i++)
                fw.write(str.charAt(i));
            System.out.println("Successfully written");
            fw.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        return str;
    }

}