package nuts.study.webapiarchive.simpleinventoryservice.domain.log;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    @Transactional
    public void save(String message) {
        Log log = Log.of(message);
        logRepository.save(log);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void failingSave(String message) {
        Log log = Log.of(message);
        logRepository.save(log);
    }
}