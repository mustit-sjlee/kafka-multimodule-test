package org.example.kafkaconsumer1

import org.example.kafka.config.KafkaConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@EnableConfigurationProperties(value = arrayOf(KafkaConfig::class))
@ComponentScan( basePackages =
  arrayOf("org.example.kafka.config",
  "org.example.kafkaconsumer1")
)
@SpringBootApplication
class ModuleConsumer1Application

fun main(args: Array<String>) {
  runApplication<ModuleConsumer1Application>(*args)
}