package nuts.study.webapiarchive.simpleorderservice.domain.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private PlatformTransactionManager txManager;

    @BeforeEach
    void setUp() {
        logRepository.deleteAll();
    }

    @Test
    @DisplayName("로그 저장이 영속화되어야 한다")
    void savePersists() {
        // act
        logService.save("hello");

        // assert
        List<Log> all = logRepository.findAll();
        assertEquals(1, all.size());
        assertEquals("hello", all.get(0).getMessage());
    }

    @Test
    @DisplayName("REQUIRES_NEW로 시작한 저장은 외부 트랜잭션 롤백에도 커밋되어야 한다")
    void failingSaveCommittedWhenOuterRollsBack() {
        TransactionTemplate tt = new TransactionTemplate(txManager);

        try {
            tt.execute(status -> {
                // inside outer transaction
                logService.failingSave("from-inner-new-tx");
                // simulate failure in outer transaction
                throw new RuntimeException("outer failure");
            });
        } catch (RuntimeException ignore) {
            // expected
        }

        List<Log> all = logRepository.findAll();
        // since failingSave uses REQUIRES_NEW, it should have been committed despite outer rollback
        assertEquals(1, all.size());
        assertEquals("from-inner-new-tx", all.get(0).getMessage());
    }

}