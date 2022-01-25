package org.example.kafkaproducer1.repository

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.example.kafka.model.Data
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import java.nio.file.Paths

@Repository
class MessageRepository {

  fun readDBfile() : ArrayList<Data> {
    val resource = ClassPathResource("DB.json")
    val file = Paths.get(resource.uri).toFile()
    val objectMapper = jacksonObjectMapper()
    val datalist = objectMapper.readValue<ArrayList<Data>>(file)
    return datalist
  }

}