package com.syntax.interview.repository;

import com.syntax.interview.domain.ChangeSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChangeSetRepository extends JpaRepository<ChangeSet, Long> {

    Optional<ChangeSet> findOneById(Long id);
}
