package orderme.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_tables",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "table_id")
    )
    private Set<Tables> tables;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email); // Usa solo campos primitivos o no relacionados
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email);
    }
}
