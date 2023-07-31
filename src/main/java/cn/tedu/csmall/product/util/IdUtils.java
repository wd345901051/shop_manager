package cn.tedu.csmall.product.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public final class IdUtils {

    private IdUtils() {}

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private static final Random random = new Random();

    public static Long getId() {
        String dateTimeString = dateTimeFormatter.format(LocalDateTime.now());
        int randomNumber = random.nextInt(89) + 10;
        return Long.parseLong(dateTimeString + randomNumber);
    }
}
