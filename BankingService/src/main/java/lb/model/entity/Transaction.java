package lb.model.entity;

import com.google.common.base.Objects;
import lb.model.enums.TransactionType;

import javax.persistence.Entity;import javax.persistence.EnumType;import javax.persistence.Enumerated;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.JoinColumn;import javax.persistence.ManyToOne;import javax.persistence.Table;import java.lang.Double;import java.lang.Long;import java.lang.Override;import java.lang.String;

/**
 * Created by root on 19.12.2015.
 * Transaction entity describe
 * "transactions" at bank
 * <li>{@link #id}</li>
 * <li>{@link #account}</li>
 * <li>{@link #operationValue}</li>
 * <li>{@link #info}</>
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Persistent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(value = EnumType.ORDINAL)
    private TransactionType transactionType;

    /**
     * Getter for property 'targetAccount'.
     *
     * @return Value for property 'targetAccount'.
     */
    public String getTargetAccount() {
        return targetAccount;
    }

    /**
     * Setter for property 'targetAccount'.
     *
     * @param targetAccount Value to set for property 'targetAccount'.
     */
    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    private String targetAccount;

    private Double operationValue;

    private String info;

    public Transaction() {
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for property 'info'.
     *
     * @return Value for property 'info'.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Setter for property 'info'.
     *
     * @param info Value to set for property 'info'.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Getter for property 'operationValue'.
     *
     * @return Value for property 'operationValue'.
     */

    public Double getOperationValue() {
        return operationValue;
    }

    /**
     * Setter for property 'operationValue'.
     *
     * @param operationValue Value to set for property 'operationValue'.
     */
    public void setOperationValue(Double operationValue) {
        this.operationValue = operationValue;
    }

    /**
     * Getter for property 'transactionType'.
     *
     * @return Value for property 'transactionType'.
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Setter for property 'transactionType'.
     *
     * @param transactionType Value to set for property 'transactionType'.
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Getter for property 'account'.
     *
     * @return Value for property 'account'.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Setter for property 'account'.
     *
     * @param account Value to set for property 'account'.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "account=" + account +
                ", id=" + id +
                ", transactionType=" + transactionType +
                ", operationValue=" + operationValue +
                ", info='" + info + '\'' +
                '}';
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.account, that.account) &&
                Objects.equal(this.transactionType, that.transactionType) &&
                Objects.equal(this.operationValue, that.operationValue) &&
                Objects.equal(this.info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, account, transactionType, operationValue, info);
    }
}
