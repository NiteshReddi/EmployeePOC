package src.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import src.entity.Employee;
import src.error.EmployeeNotFoundException;
import src.repository.EmployeeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        Employee employee = Employee.builder()
                .empName("Nitesh")
                .empAddress("Hyderabad")
                .empContact("123456789999")
                .empEmail("Nitesh@gmail.com")
                .empID(2L)
                .build();

        Mockito.when(employeeRepository.findByEmpName("Nitesh"))
                .thenReturn(employee);

        Mockito.when(employeeRepository.findById(2L))
                .thenReturn(Optional.ofNullable(employee));

    }

    @Test
    @DisplayName("Get Data Based on Valid Employee Name")
    public void  ValidEmployeeName(){
        String employeeName = "Nitesh";
        Employee found = employeeService.fetchEmployeeByName(employeeName);
        assertEquals(employeeName, found.getEmpName());
    }

    @Test
    @DisplayName("Get Data Based on Valid Employee ID")
    public void  ValidEmployeeID() throws EmployeeNotFoundException {
        Long employeeID = 2L;
        Employee found = employeeService.fetchEmployeeById(employeeID);
        assertEquals(employeeID, found.getEmpID());
    }

}