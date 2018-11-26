package com.github.carniwar.springboot.scalable.rest.support;

import com.github.carniwar.springboot.scalable.rest.RestConfigTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Base class to run tests on Rest module. Initialize all context needed and contains utilities methods to support unit and integration testing.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestConfigTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RestTestSupport {

}