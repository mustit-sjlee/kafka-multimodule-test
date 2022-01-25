package org.example.kafka.producer


interface KafkaProducer {
  fun produce() : String?
}