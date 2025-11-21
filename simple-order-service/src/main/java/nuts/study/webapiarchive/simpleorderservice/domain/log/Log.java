package nuts.study.webapiarchive.simpleorderservice.domain.log;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 도메인 모델이지만 JPA ORM 매핑의 편의를 위해
 * JPA 어노테이션을 예외적으로 허용
 */
@Entity
public record Log(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        String message
) {
    // 새 로그를 생성할 때 id를 생략할 수 있게 하는 보조 생성자
    public Log(String message) {
        this(null, message);
    }

    // 편의 팩토리 메서드
    public static Log of(String message) {
        return new Log(message);
    }

}