package ru.practicum.mapper;

import ru.practicum.ewm.stats.avro.EventSimilarityAvro;

import java.time.Instant;

public final class EventSimilarityAvroMapper {
    /**
     * Don't let anyone instantiate this class.
     */
    private EventSimilarityAvroMapper() {
    }

    public static EventSimilarityAvro mapToEventSimilarityAvro(long eventA, long eventB, double score) {
        long first = Math.min(eventA, eventB);
        long second = Math.max(eventA, eventB);

        return EventSimilarityAvro.newBuilder()
                .setEventA(first)
                .setEventB(second)
                .setScore(score)
                .setTimestamp(Instant.now())
                .build();
    }
}
