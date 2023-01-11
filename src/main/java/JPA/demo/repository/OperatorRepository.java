package JPA.demo.repository;

import JPA.demo.entity.Operators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OperatorRepository extends JpaRepository<Operators, Integer> {
}
