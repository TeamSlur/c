package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Kafka;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ReqAddChatsDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<Long, ReqAddChatsDto> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "43.200.253.97:9092");
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        // Key 및 Value Deserializer 설정
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        // JsonDeserializer 설정
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ReqAddChatsDto.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        // ErrorHandlingDeserializer 설정
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(
                configProps,
                new LongDeserializer(), // Key Deserializer
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ReqAddChatsDto.class)) // Value Deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, ReqAddChatsDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, ReqAddChatsDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}