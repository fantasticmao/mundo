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

    @NativeQuery("SELECT * FROM t_user WHERE id = ?1")
    User findUserById(@RoutingSeed Integer id);

    @RoutingSeed("2")
    @NativeQuery("SELECT * FROM t_user WHERE name = 'Bob'")
    User findBob();

    @NativeQuery("SELECT * FROM t_user LIMIT 1")
    User findFirst();

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
