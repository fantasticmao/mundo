package cn.fantasticmao.mundo.data.partition;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;

/**
 * PartitionJpaRepositoryFactoryBean
 *
 * @author maodh
 * @since 2019/1/2
 */
public class PartitionJpaRepositoryFactoryBean extends JpaRepositoryFactoryBean {

    public PartitionJpaRepositoryFactoryBean(Class repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    @Nonnull
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        RepositoryFactorySupport repositoryFactorySupport = super.createRepositoryFactory(entityManager);

        PartitionDataSourcePostProcessor partitionDataSourcePostProcessor = new PartitionDataSourcePostProcessor();
        repositoryFactorySupport.addRepositoryProxyPostProcessor(partitionDataSourcePostProcessor);
        return repositoryFactorySupport;
    }
}
