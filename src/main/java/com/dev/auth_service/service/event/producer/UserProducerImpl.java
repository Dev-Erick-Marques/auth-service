package com.dev.auth_service.service.event.producer;

import com.dev.auth_service.dto.UserCreatedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UserProducerImpl implements UserProducer{
    private final KafkaTemplate<String, UserCreatedDTO> kafkaTemplate;

    @Override
    public void sendUserCreatedEvent(UUID userId) {
        UserCreatedDTO event = new UserCreatedDTO(userId);
        kafkaTemplate.send("user-created", event);
    }
}
