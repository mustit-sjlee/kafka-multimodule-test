package org.example.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.example.kafka.model.Data

interface KafkaConsumer{
  fun consume(data: ConsumerRecord<String, Data>)
}