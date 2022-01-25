package org.example.kafka.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.example.kafka.model.Data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer


@EnableKafka
@Configuration
open class KafkaConsumerConfig {

  @Autowired
  lateinit var config: KafkaConfig

  @Primary
  @Bean
  fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Data> {
    val containerFactory = ConcurrentKafkaListenerContainerFactory<String, Data>()
    containerFactory.consumerFactory = consumerFactory()
    return containerFactory
  }

  @Primary
  @Bean
  fun consumerFactory(): ConsumerFactory<String, Data> {
    return DefaultKafkaConsumerFactory(consumerProperties())
  }


  @Bean
  fun consumerProperties(): Map<String, Any> {
    return hashMapOf(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to (config.bootstrapServers ?:"localhost:9092"),
      ConsumerConfig.GROUP_ID_CONFIG to (config.consumer?.groupId ?: "Test_Group_Id"),
      ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to (config.consumer?.enableAutoCommit ?: "true"),
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to (config.consumer?.autoOffsetReset ?: "latest"),
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to (config.consumer?.keyDeserializer ?: "org.apache.kafka.common.serialization.StringDeserializer"),
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to (config.consumer?.valueDeserializer ?: "org.springframework.kafka.support.serializer.JsonDeserializer"),
      ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS to (config.consumer?.keyDeserializer ?: "org.apache.kafka.common.serialization.StringDeserializer"),
      ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to (config.consumer?.valueDeserializer ?: "org.springframework.kafka.support.serializer.JsonDeserializer"),
      JsonDeserializer.TRUSTED_PACKAGES to "*",
      JsonDeserializer.VALUE_DEFAULT_TYPE to (config.consumer?.valueDefaultType ?: "org.example.kafka.model.Data")
    )
  }
}