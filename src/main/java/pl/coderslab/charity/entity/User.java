package pl.coderslab.charity.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2)
    @NotEmpty(message = "*Please provide a username")
    private String username;

//    @Length(min = 6, message = "*Your password must have at least 6 characters")
    @NotEmpty(message = "Please provide your password")
    private String password;

    @Transient
    @NotEmpty(message = "Please retype your password")
    private String passwordConfirm;

    @Size(min = 2)
    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @Size(min = 2)
    @NotEmpty(message = "Please provide your last name")
    private String lastName;

    @Email
    @NotEmpty(message = "Please provide a valid Email")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donation_id", referencedColumnName = "id")
    private Donation donation;
}
