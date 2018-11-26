package com.github.carniwar.springboot.scalable.core.test.repository;

import com.github.carniwar.springboot.scalable.core.domain.Diff;
import com.github.carniwar.springboot.scalable.core.repository.DiffRepository;
import com.github.carniwar.springboot.scalable.core.test.support.CoreTestSupport;
import com.github.carniwar.springboot.scalable.core.util.ValidationConstraints;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.id.IdentifierGenerationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.validation.constraints.Size;
import java.util.Optional;

/**
 * Test cases for every used method from data access object for Diff entity.
 */
public class DiffRepositoryTest extends CoreTestSupport {

    @Autowired
    private DiffRepository diffRepository;

    @Test
    public void test_findById_shouldFailNullId() {
        try {
            diffRepository.findById(null);
            Assert.state(false);
        } catch (Exception e) {
            Assert.isInstanceOf(IllegalArgumentException.class, ExceptionUtils.getRootCause(e));
        }
    }

    @Test
    public void test_findById_shouldFailEmptyResult() {
        Optional<Diff> diff = diffRepository.findById("DiffRepositoryTest.test_findById_shouldFailEmptyResult");
        Assert.state(!diff.isPresent());
    }

    @Test
    public void test_findById_shouldSuccess() {
        Optional<Diff> diff = diffRepository.findById("DiffRepositoryTest.test_findById_shouldSuccess");
        Assert.state(diff.isPresent());
    }

    @Test
    public void test_save_shouldFailNullEntity() {
        try {
            diffRepository.save(null);
            Assert.state(false);
        } catch (Exception e) {
            Assert.isInstanceOf(IllegalArgumentException.class, ExceptionUtils.getRootCause(e));
        }
    }

    @Test
    public void test_save_shouldFailBlankId() {
        try {
            diffRepository.save(new Diff());
            Assert.state(false);
        } catch (Exception e) {
            Assert.isInstanceOf(IdentifierGenerationException.class, ExceptionUtils.getRootCause(e));
        }
    }

    @Test
    public void test_save_shouldFailSmallLeftSize() {
        Diff diff = new Diff("DiffRepositoryTest.test_save_shouldFailSmallLeftSize");
        diff.setLeft("");

        expectsSizeConstraintViolation(diff);
    }

    @Test
    public void test_save_shouldFailLargeLeftSize() {
        Diff diff = new Diff("DiffRepositoryTest.test_save_shouldFailLargeLeftSize");
        diff.setLeft(RandomStringUtils.random(ValidationConstraints.DIFF_DATA_SIZE_MAX+1));

        expectsSizeConstraintViolation(diff);
    }

    @Test
    public void test_save_shouldFailSmallRightSize() {
        Diff diff = new Diff("DiffRepositoryTest.test_save_shouldFailSmallRightSize");
        diff.setRight("");

        expectsSizeConstraintViolation(diff);
    }

    @Test
    public void test_save_shouldFailLargeRightSize() {
        Diff diff = new Diff("DiffRepositoryTest.test_save_shouldFailLargeRightSize");
        diff.setRight(RandomStringUtils.random(ValidationConstraints.DIFF_DATA_SIZE_MAX+1));

        expectsSizeConstraintViolation(diff);
    }

    @Test
    public void test_save_shouldSuccess() {
        Diff diff = diffRepository.save(new Diff("DiffRepositoryTest.test_save_shouldSuccess"));
        Assert.notNull(diff.getVersion());
    }

    /*
     * Private Methods
     */

    private void expectsSizeConstraintViolation(Diff diff) {
        try {
            diffRepository.save(diff);
            Assert.isTrue(false);
        } catch (Exception e) {
            assertConstraintViolation(e, Size.class);
        }
    }

}
