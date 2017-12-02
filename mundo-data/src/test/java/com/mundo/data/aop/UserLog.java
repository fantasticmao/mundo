package com.mundo.data.aop;

import com.mundo.data.domain.AbstractPartitionDomain;

/**
 * UserLog
 *
 * @author maodh
 * @since 2017/12/2
 */
public class UserLog extends AbstractPartitionDomain<Integer> {
    private static final long serialVersionUID = 8759267577493277201L;

    private Integer id;
    private Integer userId;
    private Integer type;
    private String info;

    public UserLog() {
    }

    public UserLog(Integer userId, Integer type, String info) {
        this.userId = userId;
        this.type = type;
        this.info = info;
    }

    @Override
    public Number getSeed() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", info='" + info + '\'' +
                '}';
    }

    // getter and setter

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
