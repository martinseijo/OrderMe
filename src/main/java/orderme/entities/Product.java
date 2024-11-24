package orderme.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    private ProductType productType;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<User> users;
}
