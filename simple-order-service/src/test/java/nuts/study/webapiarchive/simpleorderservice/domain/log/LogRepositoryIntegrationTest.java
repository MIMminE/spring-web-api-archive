package nuts.study.webapiarchive.simpleorderservice.domain.log;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LogRepositoryIntegrationTest {

    @Autowired
    private LogRepository logRepository;

    @Test
    void save_and_findById_shouldWork() {
        Log log = Log.of("integration test log");
        Log saved = logRepository.save(log);

        assertThat(saved.id()).isNotNull();
        assertThat(logRepository.findById(saved.id())).isPresent();
    }
}
