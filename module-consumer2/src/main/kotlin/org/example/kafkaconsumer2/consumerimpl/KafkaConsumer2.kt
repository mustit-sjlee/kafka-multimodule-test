package org.example.kafkaconsumer2.consumerimpl

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.example.kafka.consumer.KafkaConsumer
import org.example.kafka.model.Data
import org.example.kafkaconsumer2.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
class KafkaConsumer2 : KafkaConsumer {

  @Autowired
  lateinit var messageRepository: MessageRepository

  @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"])
  override fun consume(data: ConsumerRecord<String, Data>) {
    val value = data.value()
    var datalist = messageRepository.readDBfile()
    datalist.add(value)
    messageRepository.writeDBfile(datalist)
  }

}