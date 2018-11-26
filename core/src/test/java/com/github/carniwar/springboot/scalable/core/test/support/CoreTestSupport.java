package com.github.carniwar.springboot.scalable.core.test.support;

import com.github.carniwar.springboot.scalable.api.enumeration.MessageCode;
import com.github.carniwar.springboot.scalable.api.exception.BusinessException;
import com.github.carniwar.springboot.scalable.core.test.CoreConfigTest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Base class to run tests on Core module. Initialize all context needed and contains utilities methods to support unit and integration testing.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreConfigTest.class)
public abstract class CoreTestSupport {

    public void assertConstraintViolation(Exception e, Class<?> validation) {
        Throwable rootCause = ExceptionUtils.getRootCause(e);
        Assert.isInstanceOf(ConstraintViolationException.class, rootCause);

        ConstraintViolationException cve = (ConstraintViolationException)rootCause;
        try {
            String messageTemplate = validation.getDeclaredMethod("message").getDefaultValue().toString();
            for (ConstraintViolation cv : cve.getConstraintViolations()) {
                if (messageTemplate.equals(cv.getMessageTemplate()))
                    return;
            }
            Assert.state(false, String.format("Message template \"%s\" not found", messageTemplate));
        } catch (NoSuchMethodException nsme) {
            Assert.state(false, String.format("Message template not found for [validation] parameter"));
        }
    }

    public void assertBusinessException(Exception e, MessageCode code) {
        Throwable rootCause = ExceptionUtils.getRootCause(e);
        Assert.isInstanceOf(BusinessException.class, rootCause);

        BusinessException be = (BusinessException)rootCause;
        Assert.state(code.equals(be.getMessageCode()));
    }

}