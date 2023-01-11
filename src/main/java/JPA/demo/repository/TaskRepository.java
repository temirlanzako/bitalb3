package JPA.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import JPA.demo.entity.Task;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findTaskByHandledIsTrue();
    List<Task> findTaskByHandledIsFalse();

    @Query(value = "SELECT st FROM Task st where st.id BETWEEN :fromParam AND :toParam")
    List<Task> getTask(@Param("fromParam") int from, @Param("toParam") int to);

    @Query(value = "SELECT tsk FROM Task tsk WHERE LOWER(tsk.userName) LIKE :nameParam ORDER BY tsk.userName ASC")
    List<Task> getTaskByAsc(String nameParam);

    @Query(value = "SELECT tsk FROM Task tsk WHERE LOWER(tsk.userName) LIKE :nameParam ORDER BY tsk.userName DESC")
    List<Task> getTaskByDesc(String nameParam);
}
