package org.example.kafka.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.example.kafka.model.Data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer


@EnableKafka
@Configuration
open class KafkaProducerConfig {

  @Autowired
  lateinit var config: KafkaConfig

  @Primary
  @Bean
  fun kafkaTemplate(): KafkaTemplate<String, Data> {
    return KafkaTemplate(producerFactory())
  }


  @Primary
  @Bean
  fun producerFactory(): ProducerFactory<String, Data> {
    return DefaultKafkaProducerFactory(producerProperties())
  }

  @Bean
  fun producerProperties(): Map<String, Any> {
    return hashMapOf(
      ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to (config.bootstrapServers ?:"localhost:9092"),
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to (config.producer?.keySerializer ?: "org.apache.kafka.common.serialization.StringSerializer"),
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to (config.producer?.valueSerializer ?: "org.springframework.kafka.support.serializer.JsonSerializer"),
      JsonSerializer.ADD_TYPE_INFO_HEADERS to (config.producer?.addTypeInfoHeader ?: false)
    )
  }
}