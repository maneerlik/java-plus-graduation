package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.enums.RequestStatus;
import ru.practicum.model.ParticipationRequest;

import java.util.*;
import java.util.stream.Collectors;

public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
    boolean existsByEventIdAndRequester(Long eventId, Long requesterId);

    long countByEventIdAndStatus(Long eventId, RequestStatus status);

    List<ParticipationRequest> findAllByEventIdAndStatus(Long eventId, RequestStatus status);

    List<ParticipationRequest> findAllByRequester(Long requesterId);

    List<ParticipationRequest> findAllByEventId(Long eventId);

    Optional<ParticipationRequest> findByIdAndRequester(Long requestId, Long requesterId);

    List<ParticipationRequest> findAllByIdIn(List<Long> requestIds);

    @Query("SELECT r.event.id, COUNT(r.id) FROM ParticipationRequest r WHERE r.event.id IN :eventIds AND r.status = 'CONFIRMED' GROUP BY r.event.id")
    List<Object[]> countConfirmedRequestsForEventsRaw(@Param("eventIds") Set<Long> eventIds);

    default Map<Long, Long> countConfirmedRequestsForEvents(Set<Long> eventIds) {
        if (eventIds == null || eventIds.isEmpty()) {
            return Collections.emptyMap();
        }
        return countConfirmedRequestsForEventsRaw(eventIds).stream()
                .collect(Collectors.toMap(
                        obj -> (Long) obj[0],
                        obj -> (Long) obj[1]
                ));
    }
}