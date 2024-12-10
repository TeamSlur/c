package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Kafka;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ReqAddChatsDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<Long, ReqAddChatsDto> factory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<Long, ReqAddChatsDto> kafkaTemplate() {
        return new KafkaTemplate<>(factory());
    }
}