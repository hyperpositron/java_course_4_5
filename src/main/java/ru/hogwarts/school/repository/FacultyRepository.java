package ru.hogwarts.school.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

  List<Faculty> findAllByColor(String color);

  List<Faculty> findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(
      String color,
      String name
  );

}
