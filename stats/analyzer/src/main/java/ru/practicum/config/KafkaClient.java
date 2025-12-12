package ru.practicum.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.Consumer;

import java.time.Duration;

/**
 * Интерфейс предоставляет доступ к клиентским компонентам Apache Kafka, для двух консьюмеров
 * {@code ConsumerHub} и {@code ConsumerSnapshot} и вспомогательным параметрам конфигурации
 * <p>
 * Содержит методы для реализации логики создания и управления экземплярами Kafka-клиентов.
 * Обычно используется в связке с Spring-контекстом, где реализация создаётся как прототипный бин
 * (с аннотацией {@code @Scope("prototype")})
 * </p>
 *
 * @see org.apache.kafka.clients.producer.Producer
 * @see Consumer
 * @see Duration
 */

public interface KafkaClient {
    Consumer<Long, SpecificRecordBase> getConsumerAction();

    Consumer<Long, SpecificRecordBase> getConsumerSimilarity();

    Duration getPollTimeout();

    KafkaTopicsProperties getTopicsProperties();
}
