package org.example.kafkaproducer1.controller

import org.example.kafkaproducer1.producerimpl.KafkaProducer1
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class KafkaProducerController (private val kafkaProducer1: KafkaProducer1) {

  @GetMapping("/message_send")
  fun message_send() :String?{
    return kafkaProducer1.produce()
  }
}