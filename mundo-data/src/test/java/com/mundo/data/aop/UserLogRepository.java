package com.mundo.data.aop;

import com.mundo.data.datasource.PartitionSupport;
import org.springframework.data.repository.CrudRepository;

/**
 * UserLogRepository
 *
 * @author maodh
 * @since 2017/12/2
 */
public interface UserLogRepository extends PartitionSupport, CrudRepository<UserLog, Integer> {

}
