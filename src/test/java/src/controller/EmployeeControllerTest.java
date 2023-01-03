package src.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import src.entity.Employee;
import src.service.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .empName("Nitesh")
                .empAddress("Hyderabad")
                .empContact("12345678")
                .empEmail("Nitesh@gmail.com")
                .empID(2L)
                .build();
    }

    @Test
    void saveEmployee() throws Exception {
        Employee inputEmployee = Employee.builder()
                .empName("Nitesh")
                .empAddress("Hyderabad")
                .empContact("12345678")
                .empEmail("Nitesh@gmail.com")
                .empID(2L)
                .build();

        Mockito.when(employeeService.saveEmployee(inputEmployee))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"empName\":\"Nitesh\",\n" +
                        "\t\"empAddress\":\"Hyderabad\",\n" +
                        "\t\"empContact\":\"12345678\",\n" +
                        "\t\"empEmail\":\"Nitesh@gmail.com\"\n" +
                        "}"))
                        .andExpect(status().isOk());

    }

    @Test
    void fetchEmployeeById() throws Exception {
        Mockito.when(employeeService.fetchEmployeeById(2L))
                .thenReturn(employee);

        mockMvc.perform(get("/employees/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empName").value(employee.getEmpName()));
    }
}