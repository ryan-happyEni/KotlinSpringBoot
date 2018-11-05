package com.example.demo.service

import com.example.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(@Autowired val userRepository: UserRepository){
    fun find(user_seq : Long) = userRepository.findById(user_seq)
    fun find(user_id : String) = userRepository.findByUserId(user_id)
}