package org.kcsmini2.ojeommo.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 작성자: 김준연
 *
 * 설명: application.yml의 jwt 설정 값을 읽어오는 클래스
 *
 * 최종 수정 일자: 2023/07/21
 */
@Setter
@Getter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {

    private String issuer;
    private String secretKey;
}
