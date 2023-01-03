package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByEmpName(String empName);

//   public Employee findByEmpAddress(String empAddress);
//   public Employee findByEmpNameIgnoreCase(String empName);
}
