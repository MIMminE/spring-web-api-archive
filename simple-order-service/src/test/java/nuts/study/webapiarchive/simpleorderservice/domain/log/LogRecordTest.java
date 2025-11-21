package nuts.study.webapiarchive.simpleorderservice.domain.log;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogRecordTest {

    @Test
    void of_createsLog_withMessage_andNullId() {
        Log log = Log.of("order created");

        assertThat(log).isNotNull();
        assertThat(log.message()).isEqualTo("order created");
        assertThat(log.id()).isNull();
    }
}

