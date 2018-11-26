package com.github.carniwar.springboot.scalable.api.test.dto;

import com.github.carniwar.springboot.scalable.api.ApiConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.internal.utils.ReflectionUtils;

import java.io.IOException;

/**
 * Class responsible for testing all POJO characteristics for DTO on the API module.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiConfig.class)
public class ApiDTOTest {
    @Test
    public void test_DTO_ExpectsPojoMethodsAreAllWellImplemented() throws IOException {
        for (Class clazz: ReflectionUtils.getClassesFromPackage(ApiConfig.class.getPackage().getName() + ".dto")) {
            Assertions.assertPojoMethodsFor(clazz).areWellImplemented();
        }
    }
}
