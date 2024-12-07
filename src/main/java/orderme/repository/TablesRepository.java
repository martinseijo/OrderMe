package orderme.repository;

import orderme.entities.Tables;
import orderme.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TablesRepository extends JpaRepository<Tables, Integer> {

    Optional<Tables> findByUserIdAndNumber(@Param("userId") Integer userId, @Param("tableNumber") Integer tableNumber);
    List<Tables> findByUser(User user);
}
