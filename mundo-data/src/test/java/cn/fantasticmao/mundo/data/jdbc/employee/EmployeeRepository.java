package cn.fantasticmao.mundo.data.jdbc.employee;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import cn.fantasticmao.mundo.data.jdbc.NativeQuery;
import cn.fantasticmao.mundo.data.jdbc.RoutingSeed;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EmployeeRepository
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-19
 */
public interface EmployeeRepository<ID extends Number> extends JpaRepository<EmployeeRepository.Employee, ID> {

    @Nullable
    @RoutingSeed("sale")
    @NativeQuery("SELECT * FROM t_employee WHERE id = ?1")
    Employee findByIdInSale(@Nonnull ID id);

    @Nullable
    @RoutingSeed("tech")
    @NativeQuery("SELECT * FROM t_employee WHERE id = ?1")
    Employee findByIdInTech(@Nonnull ID id);

    @Getter
    @Setter
    @Entity
    @Table(name = "t_employee")
    class Employee extends AbstractDomain<Integer> {
        @Id
        private Integer id;
        private String name;

        @Override
        public String toString() {
            return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "} " + super.toString();
        }
    }
}