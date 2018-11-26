package com.github.carniwar.springboot.scalable.core.domain;

import com.github.carniwar.springboot.scalable.core.domain.support.EntitySupport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import static com.github.carniwar.springboot.scalable.core.util.ValidationConstraints.*;

/**
 * Diff entity used to store left and right data on database.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diff extends EntitySupport {

    @Id
    @Size(min = DIFF_ID_SIZE_MIN, max = DIFF_ID_SIZE_MAX)
    private String id;

    @Size(min = DIFF_DATA_SIZE_MIN, max = DIFF_DATA_SIZE_MAX)
    private String left;

    @Size(min = DIFF_DATA_SIZE_MIN, max = DIFF_DATA_SIZE_MAX)
    private String right;

    public Diff(String id) {
        this.id = id;
    }
}
