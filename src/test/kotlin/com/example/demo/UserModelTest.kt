package com.example.demo

import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserModelTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun insert() {
        val user = User(-1,"test","Test")
        val info = userRepository.save(user)

        println(">>>>>>>>>$info")
    }

    @Test
    fun update() {
        val user = User(1,"test1","Test1")
        val info = userRepository.save(user)

        println(">>>>>>>>>$info")
    }

    @Test
    fun delete() {
        val info = userRepository.deleteById(1)

        println(">>>>>>>>>$info")
    }

    @Test
    fun select() {
        val info = userRepository.findById(2)

        println(">>>>>>>>>$info")
    }

    @Test
    fun list() {
        val info = userRepository.findAll();

        println(">>>>>>>>>$info")
    }
}