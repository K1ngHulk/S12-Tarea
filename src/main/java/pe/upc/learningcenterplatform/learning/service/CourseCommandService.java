// CourseCommandService.java
package pe.upc.learningcenterplatform.courses.application.services;

import pe.upc.learningcenterplatform.courses.domain.model.commands.CreateCourseCommand;
import pe.upc.learningcenterplatform.courses.domain.model.commands.UpdateCourseCommand;
import pe.upc.learningcenterplatform.courses.domain.model.commands.DeleteCourseCommand;
import pe.upc.learningcenterplatform.courses.domain.model.entities.Course;
import pe.upc.learningcenterplatform.courses.domain.repository.CourseRepository;

public class CourseCommandService {

    private final CourseRepository courseRepository;

    public CourseCommandService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course handle(CreateCourseCommand command) {
        Course newCourse = new Course(
            command.title(),
            command.description(),
            command.instructor(),
            command.duration(),
            command.level(),
            command.category()
        );
        return courseRepository.save(newCourse);
    }

    public Course handle(UpdateCourseCommand command) {
        Course existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        existingCourse.updateDetails(
            command.title(),
            command.description(),
            command.instructor(),
            command.duration(),
            command.level(),
            command.category()
        );
        return courseRepository.save(existingCourse);
    }

    public void handle(DeleteCourseCommand command) {
        Course course = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        courseRepository.delete(course);
    }
}
