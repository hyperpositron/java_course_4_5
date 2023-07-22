package ru.hogwarts.school.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.mapper.StudentMapper;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

@Service
public class FacultyService {

  private final FacultyRepository facultyRepository;
  private final StudentRepository studentRepository;
  private final FacultyMapper facultyMapper;
  private final StudentMapper studentMapper;

  public FacultyService(FacultyRepository facultyRepository,
      StudentRepository studentRepository,
      FacultyMapper facultyMapper,
      StudentMapper studentMapper) {
    this.facultyRepository = facultyRepository;
    this.studentRepository = studentRepository;
    this.facultyMapper = facultyMapper;
    this.studentMapper = studentMapper;
  }

  public FacultyDtoOut create(FacultyDtoIn facultyDtoIn) {
    return facultyMapper.toDto(
        facultyRepository.save(
            facultyMapper.toEntity(facultyDtoIn)
        )
    );
  }

  public FacultyDtoOut update(long id, FacultyDtoIn facultyDtoIn) {
    return facultyRepository.findById(id)
        .map(oldFaculty -> {
          oldFaculty.setColor(facultyDtoIn.getColor());
          oldFaculty.setName(facultyDtoIn.getName());
          return facultyMapper.toDto(facultyRepository.save(oldFaculty));
        })
        .orElseThrow(() -> new FacultyNotFoundException(id));
  }

  public FacultyDtoOut delete(long id) {
    Faculty faculty = facultyRepository.findById(id)
        .orElseThrow(() -> new FacultyNotFoundException(id));
    facultyRepository.delete(faculty);
    return facultyMapper.toDto(faculty);
  }

  public FacultyDtoOut get(long id) {
    return facultyRepository.findById(id)
        .map(facultyMapper::toDto)
        .orElseThrow(() -> new FacultyNotFoundException(id));
  }

  public List<FacultyDtoOut> findAll(@Nullable String color) {
    return Optional.ofNullable(color)
        .map(facultyRepository::findAllByColor)
        .orElseGet(facultyRepository::findAll).stream()
        .map(facultyMapper::toDto)
        .collect(Collectors.toList());
  }

  public List<FacultyDtoOut> findByColorOrName(String colorOrName) {
    return facultyRepository.findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(
            colorOrName,
            colorOrName
        ).stream()
        .map(facultyMapper::toDto)
        .collect(Collectors.toList());
  }

  public List<StudentDtoOut> findStudents(long id) {
    return studentRepository.findAllByFaculty_Id(id).stream()
        .map(studentMapper::toDto)
        .collect(Collectors.toList());
  }

}
