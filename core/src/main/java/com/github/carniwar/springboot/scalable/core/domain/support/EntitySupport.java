package com.github.carniwar.springboot.scalable.core.domain.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * General behaviour for entities on the project. All <code>@Entity</code> must inherit from this class and create columns for its fields.
 * <ul>
 *     <li>created_at timestamp not null</li>
 *     <li>updated_at timestamp not null</li>
 *     <li>version int not null</li>
 * </ul>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class EntitySupport implements Serializable {

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Version
    private Integer version;

    @PrePersist
    protected void prePersist() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
