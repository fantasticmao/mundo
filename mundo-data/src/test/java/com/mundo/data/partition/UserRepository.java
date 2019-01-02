package com.mundo.data.partition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 *
 * @author maodh
 * @since 2019/1/1
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM test_user LIMIT 1", nativeQuery = true)
    User findTop(@PartitionParam Integer userId);
}
