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
class UserControllerTest {
    
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun info() {
        var url = "/user/info/"
        var user_id = "test";
        url += user_id;

        val result = testRestTemplate.getForEntity(url, String::class.java)
        Assert.assertNotNull(result)
        Assert.assertEquals(HttpStatus.OK, result.statusCode)
		
		println(">>>\n$result\n<<<")
	}

    @Test
    fun infoError() {
        var url = "/user/info/"
        var user_id = "test1";
        url += user_id;

        val result = testRestTemplate.getForEntity(url, String::class.java)
        Assert.assertNotNull(result)
        Assert.assertEquals(HttpStatus.OK, result.statusCode)

        println(">>>\n$result\n<<<")
    }
}