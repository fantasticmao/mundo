package cn.fantasticmao.mundo.data.jdbc.employee;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import cn.fantasticmao.mundo.data.jdbc.RoutingSeed;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
    Employee findByIdInSale(@NonNull @Param("id") ID id);

    @Nullable
    @RoutingSeed(DEPARTMENT_TECH)
    @Query("SELECT * FROM t_employee WHERE id = :id")
    Employee findByIdInTech(@NonNull @Param("id") ID id);

    @Getter
    @Setter
    @Table("t_employee")
    class Employee extends AbstractEntity<Integer> {
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
