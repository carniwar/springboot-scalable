package com.github.carniwar.springboot.scalable.core.repository;

import com.github.carniwar.springboot.scalable.core.domain.Diff;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access object responsible for CRUD operations over <code>Diff</code> entity.
 */
public interface DiffRepository extends JpaRepository<Diff, String> {
}
