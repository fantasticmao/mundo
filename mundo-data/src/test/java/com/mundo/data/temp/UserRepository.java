package com.mundo.data.temp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author maodh
 * @since 15/03/2018
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
