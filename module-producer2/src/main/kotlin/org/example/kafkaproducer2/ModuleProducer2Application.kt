package org.example.kafkaproducer2

import org.example.kafka.config.KafkaConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@EnableConfigurationProperties(value = arrayOf(KafkaConfig::class))
@ComponentScan( basePackages =
arrayOf("org.example.kafka.config",
  "org.example.kafkaproducer2")
)
@SpringBootApplication
class ModuleProducer2Application

fun main(args: Array<String>) {
  runApplication<ModuleProducer2Application>(*args)
}
