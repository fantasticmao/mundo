package cn.fantasticmao.mundo.data.jdbc.user;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import cn.fantasticmao.mundo.data.jdbc.RoutingSeed;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * UserRepository
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2019-01-01
 */
@RoutingSeed("1")
public interface UserRepository<ID extends Number> extends CrudRepository<UserRepository.User, ID> {

    @Nonnull
    @Override
    <S extends User> S save(@Nonnull S user);

    @Nonnull
    @Override
    Optional<User> findById(@Nonnull @RoutingSeed ID id);

    @RoutingSeed("2")
    @Query("SELECT * FROM t_user WHERE name = 'Bob'")
    User findBob();

    @Query("SELECT * FROM t_user LIMIT 1")
    User findFirst();

    @Getter
    @Setter
    @Table("t_user")
    class User extends AbstractEntity<Integer> {
        @Id
        @RoutingSeed
        private Integer id;
        private String name;

        @Override
        public String toString() {
            return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "} " + super.toString();
        }
    }
}
