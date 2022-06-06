package woowacourse.shoppingcart.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import woowacourse.shoppingcart.dto.ErrorResponse;
import woowacourse.shoppingcart.exception.dataempty.DataEmptyException;
import woowacourse.shoppingcart.exception.dataformat.DataFormatException;
import woowacourse.shoppingcart.exception.datanotfound.DataNotFoundException;
import woowacourse.shoppingcart.exception.datanotfound.LoginDataNotFoundException;
import woowacourse.shoppingcart.exception.datanotmatch.DataNotMatchException;
import woowacourse.shoppingcart.exception.duplicateddata.DuplicatedDataException;
import woowacourse.shoppingcart.exception.token.InvalidTokenException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleUnhandledException() {
        return ResponseEntity.badRequest().body(ErrorResponse.from("예상치못한 에러가 발생했습니다."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleDataFormatError(final BindingResult bindingResult) {
        return ResponseEntity.badRequest().body(ErrorResponse.from(bindingResult));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFound(final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.from(e));
    }

    @ExceptionHandler({
            DuplicatedDataException.class,
            DataEmptyException.class,
            DataFormatException.class,
            DataNotMatchException.class
    })
    public ResponseEntity<ErrorResponse> handleInvalidData(final RuntimeException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.from(e));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidToken(final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse.from(e));
    }

    @ExceptionHandler(LoginDataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoginDataNotFound(final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.from(e));
    }
}
