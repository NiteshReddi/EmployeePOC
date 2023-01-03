package src.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import src.entity.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Employee employee = Employee.builder()
                .empName("Nitesh")
                .empAddress("Hyderabad")
                .empContact("12345678")
                .empEmail("Nitesh@gmail.com")
                .empID(2L)
                .build();
        entityManager.persist(employee);
    }
    @Test
    @DisplayName("Get Data Based on Valid Employee ID")
    public void  ValidEmployeeID(){
        Employee employee = employeeRepository.findById(2L).get();
        assertEquals(employee.getEmpAddress(),"Hyderabad");
    }
}