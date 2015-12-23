package lb.model.entity;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.*;

/**
 * Created by root on 19.12.2015.
 */
@Entity
@Table(name = "client")
public class Client implements Persistent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Temporal(value = TemporalType.DATE)
    private Date birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<Account>();



    public Client() {
    }

    /**
     * Getter for property 'accounts'.
     *
     * @return Value for property 'accounts'.
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Setter for property 'accounts'.
     *
     * @param accounts Value to set for property 'accounts'.
     */
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Getter for property 'birthDate'.
     *
     * @return Value for property 'birthDate'.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Setter for property 'birthDate'.
     *
     * @param birthDate Value to set for property 'birthDate'.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Getter for property 'firstName'.
     *
     * @return Value for property 'firstName'.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for property 'firstName'.
     *
     * @param firstName Value to set for property 'firstName'.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * Getter for property 'lastName'.
     *
     * @return Value for property 'lastName'.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for property 'lastName'.
     *
     * @param lastName Value to set for property 'lastName'.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.firstName, that.firstName) &&
                Objects.equal(this.lastName, that.lastName) &&
                Objects.equal(this.birthDate, that.birthDate) &&
                Objects.equal(this.accounts, that.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstName, lastName, birthDate, accounts);
    }

    @Override
    public String toString() {
        return "Client{" +
               // "accounts=" + accounts +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
