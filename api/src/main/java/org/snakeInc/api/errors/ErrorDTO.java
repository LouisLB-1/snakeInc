package org.snakeInc.api.errors;

import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorDTO {
    private LocalDateTime timestamp;
    private List<String> message;

    public ErrorDTO(MethodArgumentNotValidException e){
        this.timestamp = LocalDateTime.now();
        this.message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
    }
}
