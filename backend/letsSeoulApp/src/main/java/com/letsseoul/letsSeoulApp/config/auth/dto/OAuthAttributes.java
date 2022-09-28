package com.letsseoul.letsSeoulApp.config.auth.dto;

import com.letsseoul.letsSeoulApp.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Getter
public class OAuthAttributes {

    private String username;
    private String origin; // GOOGLE, NAVER, KAKAO
    private String emoji;
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickname;

    @Builder
    public OAuthAttributes(String username,
                           String origin,
                           String emoji,
                           Map<String, Object> attributes,
                           String nameAttributeKey,
                           String nickname) {

        this.username = username;
        this.origin = origin;
        this.emoji = emoji;
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
    }

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {

        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes, "NAVER");
        } else if ("kakao".equals(registrationId)) {
            return ofKakao("id", attributes, "KAKAO");
        }

        return ofGoogle(userNameAttributeName, attributes, "GOOGLE");
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes,
                                            String origin) {

        Map<String, Object> attributesAddedId = new HashMap<>(attributes);
        attributesAddedId.put("id", attributes.get("sub"));

        log.info("####### GOOGLE attributes : {} / {}", userNameAttributeName, attributesAddedId.toString()); // 테스트 로그

        return OAuthAttributes.builder()
                .username(String.valueOf(attributes.get("sub")))
                .origin(origin)
                .emoji("😎")
                .nickname(String.valueOf(attributes.get("name")))
                .attributes(attributesAddedId)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName,
                                           Map<String, Object> attributes,
                                           String origin) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        log.info("####### NAVER attributes(response) : {} / {}", userNameAttributeName, response.toString()); // 테스트 로그

        return OAuthAttributes.builder()
                .username(String.valueOf(response.get("id")))
                .origin(origin)
                .emoji("😎")
                .nickname(String.valueOf(response.get("name")))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName,
                                           Map<String, Object> attributes,
                                           String origin) {

        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        properties.put("id", attributes.get("id"));

        log.info("####### KAKAO attributes(properties) : {} / {}", userNameAttributeName, properties.toString()); // 테스트 로그

        return OAuthAttributes.builder()
                .username(String.valueOf(attributes.get("id")))
                .origin(origin)
                .emoji("😎")
                .nickname(String.valueOf(properties.get("nickname")))
                .attributes(properties)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {

        return User.builder()
                .username(username)
                .origin(origin)
                .emoji(emoji)
                .name(nickname)
                .role("USER")
                .build();
    }
}
