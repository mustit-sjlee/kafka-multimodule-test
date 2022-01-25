package org.example.kafkaproducer2.producerimpl


import org.example.kafkaproducer2.repository.MessageRepository
import org.apache.kafka.clients.producer.ProducerRecord
import org.example.kafka.model.Data
import org.example.kafka.producer.KafkaProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaProducer2(val kafkaTemplate: KafkaTemplate<String, Data>)
  : KafkaProducer{

  @Value("\${spring.kafka.template.default-topic}")
  lateinit var TOPIC_NAME: String

  @Autowired
  lateinit var messageRepository: MessageRepository

  override fun produce() : String? {
    try {
      val datalist = messageRepository.readDBfile()
      for (data in datalist) {
        kafkaTemplate!!.send(ProducerRecord(TOPIC_NAME, data))
      }
    } catch (e: Exception) {
      return e.message
    }
    return "Completed!"
  }

}