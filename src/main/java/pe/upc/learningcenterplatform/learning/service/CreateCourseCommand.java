package pe.upc.learningcenterplatform.courses.domain.model.commands;

public record CreateCourseCommand(String title, String description, String instructor, int duration, String level, String category) {
}