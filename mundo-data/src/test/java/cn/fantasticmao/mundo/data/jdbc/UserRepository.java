package cn.fantasticmao.mundo.data.jdbc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserRepository
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2019/1/1
 */
@RoutingSeed("1")
public interface UserRepository extends JpaRepository<UserRepository.User, Integer> {

    @Getter
    @Setter
    @Entity
    @Table(name = "t_user")
    class User extends AbstractDomain<Integer> {
        private String name;

        public User() {
        }

        @Override
        public String toString() {
            return "User{" +
                "name='" + name + '\'' +
                "} " + super.toString();
        }
    }
}
