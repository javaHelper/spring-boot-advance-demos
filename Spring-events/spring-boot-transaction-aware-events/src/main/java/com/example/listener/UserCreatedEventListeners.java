package com.example.listener;

import com.example.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserCreatedEventListeners {
    private static final Logger logger = LoggerFactory.getLogger(UserCreatedEventListeners.class);

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleBeforeCommit(UserCreatedEvent event) {
        logger.info("BEFORE COMMIT: Preparing to commit user creation - {}", event.getUsername());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAfterCommit(UserCreatedEvent event) {
        logger.info("AFTER COMMIT: Successfully created user - {}", event.getUsername());
        // This will ONLY be called if the transaction commits
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleAfterRollback(UserCreatedEvent event) {
        logger.info("AFTER ROLLBACK: User creation failed - {}", event.getUsername());
        // This will ONLY be called if the transaction rolls back
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void handleAfterCompletion(UserCreatedEvent event) {
        logger.info("AFTER COMPLETION: User creation process completed - {}", event.getUsername());
        // This will be called for both commit and rollback
    }
}