package ru.hogwarts.school.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

  Optional<Avatar> findByStudent_Id(long studentId);

}
