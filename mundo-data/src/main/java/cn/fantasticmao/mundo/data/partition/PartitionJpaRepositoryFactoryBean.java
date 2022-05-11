package cn.fantasticmao.mundo.data.partition;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;

/**
 * PartitionJpaRepositoryFactoryBean
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2019/1/2
 */
public class PartitionJpaRepositoryFactoryBean<T extends Repository<S, ID>, S, ID>
    extends JpaRepositoryFactoryBean<T, S, ID> {

    public PartitionJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    @Nonnull
    protected RepositoryFactorySupport createRepositoryFactory(@Nonnull EntityManager entityManager) {
        RepositoryFactorySupport repositoryFactorySupport = super.createRepositoryFactory(entityManager);

        PartitionDataSourcePostProcessor partitionDataSourcePostProcessor = new PartitionDataSourcePostProcessor();
        repositoryFactorySupport.addRepositoryProxyPostProcessor(partitionDataSourcePostProcessor);
        return repositoryFactorySupport;
    }
}
