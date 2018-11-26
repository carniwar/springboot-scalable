package com.github.carniwar.springboot.scalable.core.test.domain;

import com.github.carniwar.springboot.scalable.core.CoreConfig;
import com.github.carniwar.springboot.scalable.core.test.support.CoreTestSupport;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.internal.utils.ReflectionUtils;

import java.io.IOException;

/**
 * Class responsible for testing all POJO characteristics for Entities on the Core module.
 */
public class CoreEntityTest extends CoreTestSupport {
    @Test
    public void test_Entity_ExpectsPojoMethodsAreAllWellImplemented() throws IOException {
        for (Class clazz: ReflectionUtils.getClassesFromPackage(CoreConfig.class.getPackage().getName() + ".domain")) {
            Assertions.assertPojoMethodsFor(clazz).areWellImplemented();
        }
    }
}
