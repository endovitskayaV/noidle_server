package noidle_server.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.DataEntity;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Integer> {
}
