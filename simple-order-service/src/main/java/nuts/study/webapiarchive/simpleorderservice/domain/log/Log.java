package nuts.study.webapiarchive.simpleorderservice.domain.log;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * 도메인 모델이지만 JPA ORM 매핑의 편의를 위해
 * JPA 어노테이션을 예외적으로 허용
 */
@Entity
@Getter
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String message;

    // 편의 팩토리 메서드
    public static Log of(String message) {
        Log log = new Log();
        log.message = message;
        return log;
    }
}