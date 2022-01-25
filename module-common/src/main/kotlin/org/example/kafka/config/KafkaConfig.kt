package org.example.kafka.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "spring.kafka")
data class KafkaConfig (
  var bootstrapServers: String?,
  var producer: Producer?,
  var consumer: Consumer?,
  var template: Template? ){

  data class Producer(
    var keySerializer: String?,
    var valueSerializer: String?,
    var addTypeInfoHeader: Boolean?
  )

  data class Template (
    var defaultTopic: String?
  )

  data class Consumer (
    var groupId: String?,
    var enableAutoCommit: String?,
    var autoOffsetReset: String?,
    var keyDeserializer: String?,
    var valueDeserializer: String?,
    var valueDefaultType: String?
  )


}