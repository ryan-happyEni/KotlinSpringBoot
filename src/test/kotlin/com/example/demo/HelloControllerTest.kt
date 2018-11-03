package com.example.demo

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {
    
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun rssTest() {
        val result = testRestTemplate.getForEntity("/hello/hello", String::class.java)
        Assert.assertNotNull(result)
        Assert.assertEquals(HttpStatus.OK, result.statusCode)
		
		println(">>>>>>>>>$result")
	}
}