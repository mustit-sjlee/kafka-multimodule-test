package org.example.kafkaproducer2.controller

import org.example.kafkaproducer2.producerimpl.KafkaProducer2
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class KafkaProducerController (private val kafkaProducer2: KafkaProducer2) {

  @GetMapping("/message_send")
  fun message_send() :String?{
    return kafkaProducer2.produce()
  }
}