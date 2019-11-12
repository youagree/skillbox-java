package ru.skillbox.tdl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.tdl.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
