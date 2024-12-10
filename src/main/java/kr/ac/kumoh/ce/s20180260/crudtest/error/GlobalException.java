package kr.ac.kumoh.ce.s20180260.crudtest.error;

// Spring Framework에서 제공하는 ControllerAdvice 애너테이션을 사용하여
// 전역 예외 처리 클래스로 지정합니다.
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 모든 Controller에서 발생하는 예외를 처리하기 위한 전역 예외 처리 클래스입니다..
@ControllerAdvice
public class GlobalException {

    // ExceptionHandler 애너테이션은 특정 예외가 발생했을 때 해당 메서드를 실행하도록 설정합니다.
    // 여기서는 모든 예외(Exception.class)를 처리하는 메서드입니다.
    @ExceptionHandler(Exception.class)
    public String exception1(Exception e) {
        // 발생한 예외의 스택 트레이스를 콘솔에 출력하여 디버깅에 도움을 줍니다.
        e.printStackTrace();

        // 에러가 발생했을 때 "/error/404" 뷰 페이지로 이동하도록 설정합니다.
        return "/error/404";
    }
}