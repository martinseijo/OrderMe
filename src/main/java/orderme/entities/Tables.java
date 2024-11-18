package orderme.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tables")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Tables implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer number;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String description;

    @ManyToMany(mappedBy = "tables", fetch = FetchType.LAZY)
    private Set<User> users;

    @Override
    public int hashCode() {
        return Objects.hash(id, number); // Usa solo campos primitivos o no relacionados
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tables table = (Tables) o;
        return Objects.equals(id, table.id) &&
                Objects.equals(number, table.number);
    }
}
