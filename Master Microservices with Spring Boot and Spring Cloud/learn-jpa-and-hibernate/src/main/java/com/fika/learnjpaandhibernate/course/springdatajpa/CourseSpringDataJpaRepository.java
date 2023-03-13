package com.fika.learnjpaandhibernate.course.springdatajpa;

import com.fika.learnjpaandhibernate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {
    // defining a custom method
    List<Course> findByAuthor(String author);
}
