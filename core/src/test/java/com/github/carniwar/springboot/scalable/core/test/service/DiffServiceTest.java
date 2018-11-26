package com.github.carniwar.springboot.scalable.core.test.service;

import com.github.carniwar.springboot.scalable.api.dto.DiffResultDTO;
import com.github.carniwar.springboot.scalable.api.enumeration.MessageCode;
import com.github.carniwar.springboot.scalable.api.exception.BusinessException;
import com.github.carniwar.springboot.scalable.core.domain.Diff;
import com.github.carniwar.springboot.scalable.core.service.DiffService;
import com.github.carniwar.springboot.scalable.core.test.support.CoreTestSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

/**
 * Test cases for all business methods for Diff entity.
 */
public class DiffServiceTest extends CoreTestSupport {

    @Autowired
    private DiffService diffService;

    @Test
    public void test_findById_shouldFailNullId() {
        try {
            diffService.findById(null);
            Assert.state(false);
        } catch (Exception e) {
            assertConstraintViolation(e, NotBlank.class);
        }
    }

    @Test
    public void test_findById_shouldFailEmptyResult() {
        Diff diff = diffService.findById("DiffServiceTest.test_findById_shouldFailEmptyResult");
        Assert.state(diff ==null);
    }

    @Test
    public void test_findById_shouldSuccess() {
        Diff diff = diffService.findById("DiffServiceTest.test_findById_shouldSuccess");
        Assert.state(diff != null);
        Assert.state(diff.getId() != null);
    }

    @Test
    public void test_saveLeft_shouldFailNullId() {
        try {
            diffService.saveLeft(null,"DiffServiceTest.test_saveLeft_shouldFailNullId");
            Assert.state(false);
        } catch (Exception e) {
            assertConstraintViolation(e, NotBlank.class);
        }
    }

    @Test
    public void test_saveLeft_shouldFailNotBase64Data() {
        try {
            diffService.saveLeft("DiffServiceTest.test_saveLeft_shouldFailNotBase64Data","???");
            Assert.state(false);
        } catch (Exception e) {
            assertBusinessException(e, MessageCode.DATA_NOT_BASE64);
        }
    }

    @Test
    public void test_saveLeft_shouldSuccessInsert() {
        Diff diff = diffService.saveLeft("DiffServiceTest.test_saveLeft_shouldSuccessInsert","ABCDEF1234567890");
        Assert.state(diff.getVersion() != null);
    }

    @Test
    public void test_saveLeft_shouldSuccessUpdate() {
        Diff diff = diffService.saveLeft("DiffServiceTest.test_saveLeft_shouldSuccessUpdate","ABCDEF1234567890");
        Assert.state(diff.getVersion() != null);
    }

    @Test
    public void test_saveRight_shouldFailNullId() {
        try {
            diffService.saveRight(null,"DiffServiceTest.test_saveRight_shouldFailNullId");
            Assert.state(false);
        } catch (Exception e) {
            assertConstraintViolation(e, NotBlank.class);
        }
    }

    @Test
    public void test_saveRight_shouldFailNotBase64Data() {
        try {
            diffService.saveRight("DiffServiceTest.test_saveRight_shouldFailNotBase64Data","???");
            Assert.state(false);
        } catch (Exception e) {
            assertBusinessException(e, MessageCode.DATA_NOT_BASE64);
        }
    }

    @Test
    public void test_saveRight_shouldSuccessInsert() {
        Diff diff = diffService.saveRight("DiffServiceTest.test_saveRight_shouldSuccessInsert","ABCDEFabcdef1234567890");
        Assert.state(diff.getVersion() != null);
    }

    @Test
    public void test_saveRight_shouldSuccessUpdate() {
        Diff diff = diffService.saveRight("DiffServiceTest.test_saveRight_shouldSuccessUpdate","ABCDEFabcdef1234567890");
        Assert.state(diff.getVersion() != null);
    }

    @Test
    public void test_compare_shouldFailNullId() {
        try {
            diffService.compare(null);
            Assert.state(false);
        } catch (Exception e) {
            assertConstraintViolation(e, NotBlank.class);
        }
    }

    @Test
    public void test_compare_shouldFailEmptyResult() {
        try {
            diffService.compare("DiffServiceTest.test_compare_shouldFailEmptyResult");
        } catch (BusinessException be) {
            assertBusinessException(be, MessageCode.DIFF_DATA_NOT_FOUND);
        }
    }

    @Test
    public void test_compare_shouldSuccess() {
        DiffResultDTO diff = diffService.compare("DiffServiceTest.test_compare_shouldSuccess");
        Assert.state(diff != null);
        Assert.state(diff.getId() != null);
    }

    @Test
    public void test_compare_shouldSuccessEquals() {
        DiffResultDTO diff = diffService.compare("DiffServiceTest.test_compare_shouldSuccessEquals");
        Assert.state(diff != null);
        Assert.state(diff.getId() != null);
        Assert.state(diff.getEquals());
    }

    @Test
    public void test_compare_shouldSuccessSameSize() {
        DiffResultDTO diff = diffService.compare("DiffServiceTest.test_compare_shouldSuccessSameSize");
        Assert.state(diff != null);
        Assert.state(diff.getId() != null);
        Assert.state(diff.getSize());
    }

    @Test
    public void test_compare_shouldSuccessFirstNoIndex() {
        DiffResultDTO diff = diffService.compare("DiffServiceTest.test_compare_shouldSuccessFirstNoIndex");
        Assert.state(diff != null);
        Assert.state(diff.getId() != null);
        Assert.state(diff.getIndexFirstDiff() == -1);
    }

    @Test
    public void test_compare_shouldSuccessFirstIndex3() {
        DiffResultDTO diff = diffService.compare("DiffServiceTest.test_compare_shouldSuccessFirstIndex3");
        Assert.state(diff != null);
        Assert.state(diff.getId() != null);
        Assert.state(diff.getIndexFirstDiff() == 3);
    }

}
