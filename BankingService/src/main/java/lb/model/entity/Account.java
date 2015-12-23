package lb.model.entity;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19.12.2015.
 */
@Entity
@Table(name = "account")
public class Account implements Persistent {

       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       private Long id;

       @ManyToOne
       @JoinColumn(name = "client_id", nullable = false)
       private Client client;

       @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
       private List<Transaction> transactions = new ArrayList<Transaction>();

       private Double balance;



       private String accountNumber = String.valueOf(System.currentTimeMillis());

         public Account() {
        }

    /**
     * Getter for property 'accountNumber'.
     *
     * @return Value for property 'accountNumber'.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Setter for property 'accountNumber'.
     *
     * @param accountNumber Value to set for property 'accountNumber'.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }



    /**
     * Getter for property 'balance'.
     *
     * @return Value for property 'balance'.
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Setter for property 'balance'.
     *
     * @param balance Value to set for property 'balance'.
     */
    public void setBalance(Double balance) {
        this.balance = balance;
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
     * Getter for property 'owner'.
     *
     * @return Value for property 'owner'.
     */

    /**
     * Getter for property 'transactions'.
     *
     * @return Value for property 'transactions'.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Setter for property 'transactions'.
     *
     * @param transactions Value to set for property 'transactions'.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account that = (Account) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.client, that.client) &&
                Objects.equal(this.transactions, that.transactions) &&
                Objects.equal(this.balance, that.balance) &&
                Objects.equal(this.accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, client, transactions, balance, accountNumber);
    }
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", id=" + id +
                ", owner=" + client +
                ", transactions=" + transactions +
                ", balance=" + balance +
                '}';
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Getter for property 'client'.
     *
     * @return Value for property 'client'.
     */
    public Client getClient() {
        return client;
    }
}
