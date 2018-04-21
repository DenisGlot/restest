package ru.moysklad.dao;

import javax.persistence.*;

@Entity
@Table(name = "bank_operation")
public class HibernateBankOpertaion implements IBankOpertaion {

    private int id;
    private int account_id;
    private long sum;

    @Override
    @Id
    @SequenceGenerator(name="nextOperationId",sequenceName="operation_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="nextOperationId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @ManyToOne(targetEntity=HibernateBankAccount.class, fetch=FetchType.LAZY)
    @JoinColumn(name="id")
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
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
