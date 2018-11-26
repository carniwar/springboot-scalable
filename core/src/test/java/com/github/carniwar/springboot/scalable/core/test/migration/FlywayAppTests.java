package com.github.carniwar.springboot.scalable.core.test.migration;

import com.github.carniwar.springboot.scalable.core.test.support.CoreTestSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class that select count(*) from every table in the database just to check if it exists.
 */
public class FlywayAppTests extends CoreTestSupport {

    @Autowired
    private JdbcTemplate template;

    @Test
    public void testDefaultSettings() {
        assertThat(this.template.queryForObject("SELECT COUNT(*) from Diff", Integer.class)).isNotNegative();
    }

}