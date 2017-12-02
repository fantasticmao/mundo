package com.mundo.data.aop;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author maodh
 * @since 2017/12/2
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
