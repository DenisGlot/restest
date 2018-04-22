package ru.moysklad.dao;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class HibernateBankAccount implements IBankAccount {

    private int id;
    private long sum;

    @Override
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @Column(name = "sum")
    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
}
