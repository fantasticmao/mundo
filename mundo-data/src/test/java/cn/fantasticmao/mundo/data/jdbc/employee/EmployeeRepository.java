package cn.fantasticmao.mundo.data.jdbc.employee;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import cn.fantasticmao.mundo.data.jdbc.RoutingSeed;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * EmployeeRepository
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-19
 */
public interface EmployeeRepository<ID extends Number> extends CrudRepository<EmployeeRepository.Employee, ID> {

    String DEPARTMENT_SALE = "sale";

    String DEPARTMENT_TECH = "tech";

    @Nullable
    @RoutingSeed(DEPARTMENT_SALE)
    @Query("SELECT * FROM t_employee WHERE id = :id")
    Employee findByIdInSale(@Nonnull @Param("id") ID id);

    @Nullable
    @RoutingSeed(DEPARTMENT_TECH)
    @Query("SELECT * FROM t_employee WHERE id = :id")
    Employee findByIdInTech(@Nonnull @Param("id") ID id);

    @Getter
    @Setter
    @Table("t_employee")
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
