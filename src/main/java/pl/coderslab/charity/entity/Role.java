package pl.coderslab.charity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="user_role", joinColumns= @JoinColumn(name="ROLE_ID"), inverseJoinColumns = @JoinColumn(name="USER_ID"))
//    private Set<User> users;

}
