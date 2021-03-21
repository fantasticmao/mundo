package cn.fantasticmao.mundo.data.partition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Nonnull;

/**
 * UserRepository
 *
 * @author maodh
 * @version 1.0
 * @since 2019/1/1
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    @Nonnull
    User save(@Nonnull @PartitionParam User user);

    @Override
    boolean existsById(@Nonnull @PartitionParam Integer userId);

    @Override
    void delete(@Nonnull @PartitionParam User user);

    @Query(value = "SELECT * FROM test_user LIMIT 1", nativeQuery = true)
    User findTop(@PartitionParam Integer userId);
}
