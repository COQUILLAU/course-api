package com.course.courseapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.courseapi.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
}

