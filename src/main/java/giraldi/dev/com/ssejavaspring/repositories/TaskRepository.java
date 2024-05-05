package giraldi.dev.com.ssejavaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giraldi.dev.com.ssejavaspring.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
