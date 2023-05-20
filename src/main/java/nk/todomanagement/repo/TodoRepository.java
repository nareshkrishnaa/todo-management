package nk.todomanagement.repo;

import nk.todomanagement.entity.Todo;
import nk.todomanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
