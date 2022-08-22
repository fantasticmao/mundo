package cn.fantasticmao.mundo.data.jdbc.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * EmployeeRepositoryTest
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-19
 */
@SpringBootTest(classes = EmployeeDataSourceConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EmployeeRepositoryTest {
    @Resource
    private EmployeeRepository<Integer> employeeRepository;

    @Test
    public void findById() {
        EmployeeRepository.Employee employee = employeeRepository.findByIdInSale(1);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Bob", employee.getName());

        employee = employeeRepository.findByIdInTech(1);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals("Tom", employee.getName());
    }
}
