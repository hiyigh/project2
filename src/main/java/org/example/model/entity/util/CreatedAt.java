package org.example.model.entity.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@EntityScan
@Setter
@Getter
public class CreatedAt {
    private LocalDateTime createdAt = LocalDateTime.now();
}
