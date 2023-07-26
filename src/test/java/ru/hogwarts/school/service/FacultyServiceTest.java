package ru.hogwarts.school.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.mapper.AvatarMapper;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.mapper.StudentMapper;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

  private FacultyRepository facultyRepository;
  private FacultyService facultyService;

  @BeforeEach
  public void beforeEach() {
    facultyRepository = mock(FacultyRepository.class);
    FacultyMapper facultyMapper = new FacultyMapper();
    AvatarMapper avatarMapper = new AvatarMapper(8080);
    facultyService = new FacultyService(
        facultyRepository,
        mock(StudentRepository.class),
        facultyMapper,
        new StudentMapper(facultyMapper, facultyRepository, avatarMapper)
    );
  }

  @Test
  public void createTest() {
    FacultyDtoIn facultyDtoIn = new FacultyDtoIn();
    facultyDtoIn.setName("name");
    facultyDtoIn.setColor("color");

    Faculty faculty = new Faculty();
    faculty.setId(1L);
    faculty.setName("name");
    faculty.setColor("color");

    FacultyDtoOut expected = new FacultyDtoOut();
    expected.setId(1L);
    expected.setName("name");
    expected.setColor("color");

    when(facultyRepository.save(any())).thenReturn(faculty);

    FacultyDtoOut actual = facultyService.create(facultyDtoIn);

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
    verify(facultyRepository, new Times(1)).save(any());
  }

}
