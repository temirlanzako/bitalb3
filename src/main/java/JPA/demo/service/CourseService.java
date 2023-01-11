package JPA.demo.service;

import JPA.demo.entity.Course;
import JPA.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public List<Course> addCourse(){
        return  courseRepository.findAll();
    }
}
