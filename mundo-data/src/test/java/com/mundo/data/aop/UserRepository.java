package com.mundo.data.aop;

import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 *
 * @author maodh
 * @since 2017/12/2
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
