package com.dev.auth_service.service.event.producer;

import java.util.UUID;

public interface UserProducer {
    void sendUserCreatedEvent(UUID userId);
}
