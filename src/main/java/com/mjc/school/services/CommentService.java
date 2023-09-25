package com.mjc.school.services;


import com.mjc.school.services.dto.CommentRequestDTO;
import com.mjc.school.services.dto.CommentResponseDTO;

import java.util.List;

public interface CommentService extends BaseService<CommentRequestDTO, CommentResponseDTO, Long> {
    List<CommentResponseDTO> getCommentsByNewsId(Long newsId);
}
