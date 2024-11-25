package orderme.repository;

import orderme.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TablesRepository extends JpaRepository<Tables, Integer> {

    @Query(value = "SELECT t.* FROM tables t JOIN user_tables ut ON t.id = ut.table_id WHERE ut.user_id = :userId AND t.number = :tableNumber", nativeQuery = true)
    Optional<Tables> findByUserIdAndTableNumber(@Param("userId") Integer userId, @Param("tableNumber") Integer tableNumber);
}
