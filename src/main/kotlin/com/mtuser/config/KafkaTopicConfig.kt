package com.mtuser.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaTopicConfig {

    @Bean
    fun announceTopic(): NewTopic =
        NewTopic("announceItem", 3, 1)
}