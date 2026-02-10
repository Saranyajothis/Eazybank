package com.eazybytes.accounts.Audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    
    @Override
    public Optional<String> getCurrentAuditor() {
        // Here you can implement logic to retrieve the current user or system performing the operation
        // For example, you can return a fixed value for testing purposes
        return Optional.of("ACCOUNTS_MS"); // Replace with actual logic to get the current user
    }
    
}
