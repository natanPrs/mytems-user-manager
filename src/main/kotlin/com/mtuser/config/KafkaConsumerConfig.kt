package com.mtuser.config

import com.mtuser.dtos.EventUpdateUserDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
@EnableKafka
class KafkaConsumerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String
) {

    private fun <T> builderConsumerFactory(type: Class<T>): ConsumerFactory<String, T>{
        val deserializer = JsonDeserializer(type)
        deserializer.setUseTypeMapperForKey(true)

        val configs = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to "user-group",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest"
        )
        return DefaultKafkaConsumerFactory(configs, StringDeserializer(), deserializer)
    }

    private fun <T> builderKafkaListenerFactory(type: Class<T>): ConcurrentKafkaListenerContainerFactory<String, T>{
        val factory = ConcurrentKafkaListenerContainerFactory<String, T>()
        factory.consumerFactory = builderConsumerFactory(type)

        return factory
    }

    @Bean
    fun purchaseUpdateUsers(): ConcurrentKafkaListenerContainerFactory<String, EventUpdateUserDto> =
        builderKafkaListenerFactory(EventUpdateUserDto::class.java)

}