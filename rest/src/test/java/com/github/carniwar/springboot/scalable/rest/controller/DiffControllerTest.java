package com.github.carniwar.springboot.scalable.rest.controller;

import com.github.carniwar.springboot.scalable.api.enumeration.MessageCode;
import com.github.carniwar.springboot.scalable.rest.support.RestTestSupport;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Base64;

/**
 * Integration test cases for all exposed methods for Diff entity.
 */
public class DiffControllerTest extends RestTestSupport {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void test_saveLeft_shouldFailUnauthorized() {
        HttpEntity<Object> data = getHttpEntity("", null);

        ResponseEntity<String> response = template.postForEntity(getUrl(DiffController.PATH_V1+"/left", "DiffControllerTest.test_compare_shouldFailNoRight"), data, String.class);

        Assert.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void test_saveLeft_shouldFailEmptyId() {
        HttpEntity<Object> data = getHttpEntity("");

        ResponseEntity<String> response = template.postForEntity(getUrl(DiffController.PATH_V1+"/left", "DiffControllerTest.test_saveLeft_shouldFailEmptyId"), data, String.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void test_saveLeft_shouldFailEmptyData() {
        HttpEntity<Object> data = getHttpEntity("");

        ResponseEntity<String> response = template.postForEntity(getUrl(DiffController.PATH_V1+"/left", "DiffControllerTest.test_saveLeft_shouldFailEmptyData"), data, String.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Required request body is missing"));
    }

    @Test
    public void test_saveLeft_shouldSuccess() {
        HttpEntity<Object> data = getHttpEntity("RGlmZkNvbnRyb2xsZXJUZXN0");

        ResponseEntity<String> response = template.postForEntity(getUrl(DiffController.PATH_V1+"/left", "DiffControllerTest.test_saveLeft_shouldSuccess"), data, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void test_saveRight_shouldFailUnauthorized() {
        HttpEntity<Object> data = getHttpEntity("", null);
        template.withBasicAuth("", "");

        ResponseEntity<String> response = template.postForEntity(getUrl(DiffController.PATH_V1+"/right", "DiffControllerTest.test_saveRight_shouldFailEmptyId"), data, String.class);

        Assert.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void test_saveRight_shouldFailEmptyId() {
        HttpEntity<Object> data = getHttpEntity("");

        ResponseEntity<String> response = template.postForEntity(getUrl(DiffController.PATH_V1+"/right", "DiffControllerTest.test_saveRight_shouldFailEmptyId"), data, String.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void test_saveRight_shouldFailEmptyData() {
        HttpEntity<Object> data = getHttpEntity("");

        ResponseEntity<String> response = template.postForEntity(getUrl(DiffController.PATH_V1+"/right", "DiffControllerTest.test_saveRight_shouldFailEmptyData"), data, String.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Required request body is missing"));
    }

    @Test
    public void test_saveRight_shouldSuccess() {
        HttpEntity<Object> data = getHttpEntity("RGlmZkNvbnRyb2xsZXJUZXN0");

        ResponseEntity<String> response = template.postForEntity(getUrl(DiffController.PATH_V1+"/right", "DiffControllerTest.test_saveRight_shouldSuccess"), data, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void test_compare_shouldFailUnauthorized() {
        HttpEntity<Object> data = getHttpEntity(null, null);

        ResponseEntity<String> response = template.exchange(getUrl(DiffController.PATH_V1, ""), HttpMethod.GET, data, String.class);

        Assert.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void test_compare_shouldFailEmptyId() {
        HttpEntity<Object> data = getHttpEntity(null);

        ResponseEntity<String> response = template.exchange(getUrl(DiffController.PATH_V1, ""), HttpMethod.GET, data, String.class);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("No message available"));
    }

    @Test
    public void test_compare_shouldFailNotFound() {
        HttpEntity<Object> data = getHttpEntity(null);

        ResponseEntity<String> response = template.exchange(getUrl(DiffController.PATH_V1, "DiffControllerTest.test_compare_shouldFailNotFound"), HttpMethod.GET, data, String.class);

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains(MessageCode.DIFF_DATA_NOT_FOUND.name()));
    }

    @Test
    public void test_compare_shouldFailNoLeft() {
        HttpEntity<Object> data = getHttpEntity(null);

        ResponseEntity<String> response = template.exchange(getUrl(DiffController.PATH_V1, "DiffControllerTest.test_compare_shouldFailNoLeft"), HttpMethod.GET, data, String.class);

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains(MessageCode.DIFF_LEFT_DATA_BLANK.name()));
    }

    @Test
    public void test_compare_shouldFailNoRight() {
        HttpEntity<Object> data = getHttpEntity(null);

        ResponseEntity<String> response = template.exchange(getUrl(DiffController.PATH_V1, "DiffControllerTest.test_compare_shouldFailNoRight"), HttpMethod.GET, data, String.class);

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains(MessageCode.DIFF_RIGHT_DATA_BLANK.name()));
    }

    @Test
    public void test_compare_shouldSuccess() {
        HttpEntity<Object> data = getHttpEntity(null);

        ResponseEntity<String> response = template.exchange(getUrl(DiffController.PATH_V1, "DiffControllerTest.test_compare_shouldSuccess"), HttpMethod.GET, data, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /*
     * Private Methods
     */

    private HttpEntity<Object> getHttpEntity(Object body) {
        return getHttpEntity(body, "user:user");
    }

    private HttpEntity<Object> getHttpEntity(Object body, String authentication) {
        HttpHeaders headers = new HttpHeaders();
        if (StringUtils.isNotBlank(authentication))
            headers.add("Authorization", "Basic "+new String(Base64.getEncoder().encode(authentication.getBytes())));
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new HttpEntity<>(body, headers);
    }

    private String getUrl(String path, String id) {
        return path.replace("{id}", id);
    }

}
