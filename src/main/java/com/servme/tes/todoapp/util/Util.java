package com.servme.tes.todoapp.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public final class Util {
    private Util() {

    }
    public static String hashMessage(String message) {
        return Hashing.sha256()
                .hashString(message, StandardCharsets.UTF_8)
                .toString();
    }
}
