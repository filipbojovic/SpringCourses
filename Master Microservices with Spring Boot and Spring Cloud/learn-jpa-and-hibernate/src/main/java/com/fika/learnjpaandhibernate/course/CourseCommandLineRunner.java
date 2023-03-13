package com.fika.learnjpaandhibernate.course;

import com.fika.learnjpaandhibernate.course.springdatajpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {
//    @Autowired
//    private CourseJdbcRepository repository;
//    @Autowired
//    private CourseJpaRepository repository;
    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(1, "Learn Spring", "fika"));
        repository.save(new Course(2, "Learn Docker", "fika"));
        repository.save(new Course(3, "Learn K8S", "fika"));
        repository.deleteById(1L);

        System.out.println(repository.findById(3L));
        System.out.println(repository.findByAuthor("fika"));
    }
}
