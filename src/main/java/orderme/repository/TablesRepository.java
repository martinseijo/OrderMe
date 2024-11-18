package orderme.repository;

import orderme.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TablesRepository extends JpaRepository<Tables, Integer> {

    Optional<Tables> findByNumber(Integer number);
}
