package cn.fantasticmao.mundo.data.partition;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2019/1/1
 */
@Getter
@Setter
@Entity
@Table(name = "test_user")
public class User implements PartitionSeedProvider {
    @Id
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }

    @Override
    public Integer getSeed() {
        return id;
    }

}
