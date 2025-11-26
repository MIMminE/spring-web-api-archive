package nuts.study.webapiarchive.simpleinventoryservice.domain.log;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class LogServiceTest {

    @InjectMocks
    private LogService logService;

    @Mock
    private LogRepository logRepository;


    // logService save 메서드를 통해 로그가 정상적으로 저장되는지 테스트 (실제 DB에 저장되는 것은 아니고, Mock 객체를 통해 호출 여부만 확인)
    @Test
    void save_LogMessage_Success() {
        String message = "Test log message";

        // 실제 호출이 예외를 발생시키지 않는지 확인
        assertDoesNotThrow(() -> logService.save(message));

        // logRepository의 save 메서드가 호출되었는지 검증 (전달된 객체 내부 값 검증은 생략)
        verify(logRepository).save(any(Log.class));
    }

    // logService failingSave 메서드를 통해 로그가 정상적으로 저장되는지 테스트 (실제 DB에 저장되는 것은 아니고, Mock 객체를 통해 호출 여부만 확인)
    @Test
    void failingSave_LogMessage_Success() {
        String message = "Test failing log message";

        // 실제 호출이 예외를 발생시키지 않는지 확인
        assertDoesNotThrow(() -> logService.failingSave(message));

        // logRepository의 save 메서드가 호출되었는지 검증 (전달된 객체 내부 값 검증은 생략)
        verify(logRepository).save(any(Log.class));
    }
}