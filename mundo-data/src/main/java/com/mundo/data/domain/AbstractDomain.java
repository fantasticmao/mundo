package com.mundo.data.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * AbstractDomain
 *
 * @author maomao
 * @since 2017/3/5
 */
@MappedSuperclass
public abstract class AbstractDomain<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1819978692581974014L;

    @Id
    @GeneratedValue
    private ID id; // 逻辑主键
    private Timestamp createTime; // 创建时间
    private Timestamp modifyTime; // 修改时间

    @Override
    public String toString() {
        return "AbstractDomain{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public ID getId() {
        return id;
    }

    protected void setId(final ID id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    protected void setCreateTime(final Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    protected void setModifyTime(final Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
