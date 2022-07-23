package org.banking.mybankingapplication.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "customer")



public class Customer extends ExtendBase {

    private String surname;

    private String password;

    private String email;

    private String phoneNo;

    private String address;




    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "account_id"
    )
    private Set<Account> costumerAccounts;

    //private List<Card> customerCards;

    //private List<Role> costumerRoles;



    public Customer(Long id, String name, String surname, String password, String email, String phoneNo, String address) {
        super(id, name);
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.costumerAccounts = new HashSet<>();


    }
    public Customer(String name, String surname, String password, String email, String phoneNo, String address) {
        super(name);
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.costumerAccounts = new HashSet<>();

    }

    @Override
    public String toString() {
        return "Customer{" +
                "surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", costumerAccounts=" + costumerAccounts +
                '}';
    }


}
