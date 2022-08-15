package hanghae8mini.booglogbackend.exception;

import hanghae8mini.booglogbackend.dto.response.ResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseDto<?> handleNumberFormatException(){
        return ResponseDto.fail("NumberFormatException","잘못된 요청입니다. ");
    }


}
