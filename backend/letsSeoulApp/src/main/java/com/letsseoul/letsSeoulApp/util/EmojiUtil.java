package com.letsseoul.letsSeoulApp.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EmojiUtil {

    private EmojiUtil() {
        throw new IllegalStateException("유틸리티 클래스 생성 불가");
    }

    private static final String[] emoji = {
            "😁", "😃", "🤗", "😚", "🤩",
            "😛", "😋", "😘", "🥰", "😗",
            "😑", "😎", "😊", "😉", "😙",
            "😴", "😜", "😝", "😇", "🥳",
            "😬", "🥴", "🙃", "🤠", "🤓",
            "🤖", "👽", "👻", "🧐", "🤭",
            "💩", "😸", "🙈", "🐶", "🐰",
            "🐹", "🐔", "🦝", "🐻", "🐨",
            "🐼", "🐸", "🦄", "🐔", "🐾",
            "🦍", "🐈", "🦆", "🦢", "🦜",
            "🐥", "🐣", "🐌", "🐝", "👀",
            "🌞", "🐹", "",
            "🐱‍👤","🐱‍🐉","🐱‍👓","🐱‍🚀","🐱‍🏍"
    };

    public static String randomEmoji() {
        int randomNumber = 0;
        try {
            randomNumber = SecureRandom.getInstanceStrong().ints(0, emoji.length).findFirst().getAsInt();
        } catch (NoSuchAlgorithmException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서비스에 잠시 오류가 있습니다.");
        }
        return emoji[randomNumber];
    }
}
