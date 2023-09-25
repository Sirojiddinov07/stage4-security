package com.mjc.school.services.dto;

import java.time.LocalDateTime;

public record CommentResponseDTO(Long id, String content, LocalDateTime createDate,
                                 LocalDateTime lastUpdateDate, Long newsId) {
}
