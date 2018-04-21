package ru.moysklad.dao;

import javax.persistence.*;

public interface IBankOpertaion {
    @Id
    @SequenceGenerator(name="nextOperationId",sequenceName="operation_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="nextOperationId")
    int getId();

    int getAccount_id();

    @Column()
    long getSum();
}
