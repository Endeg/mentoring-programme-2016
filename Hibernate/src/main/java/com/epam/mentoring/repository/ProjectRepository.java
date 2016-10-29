package com.epam.mentoring.repository;

import com.epam.mentoring.data.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Endeg on 29.10.2016.
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
