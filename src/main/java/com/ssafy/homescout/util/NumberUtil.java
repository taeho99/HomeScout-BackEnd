package com.ssafy.homescout.util;

import java.security.SecureRandom;

public class NumberUtil {
    private static final char[] rndAllCharacters = new char[]{
            //number
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            //uppercase
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            //lowercase
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            //special symbols
            '@', '$', '!', '%', '*', '?', '&'
    };

    public static String getRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        int rndAllCharactersLength = rndAllCharacters.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(rndAllCharacters[random.nextInt(rndAllCharactersLength)]);
        }

        return stringBuilder.toString();
    }

    public static String generateAuthCode() {
        SecureRandom random = new SecureRandom();
        // 100000 ~ 999999 범위의 난수 생성
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    public static String convertPrice(String input) {
        int price = Integer.parseInt(input);

        // 억과 만 단위 계산
        int eok = (price / 10000) % 10000; // 억 단위
        int man = price % 10000; // 만 단위

        StringBuilder result = new StringBuilder();

        // 억 단위 처리
        if (eok > 0) {
            result.append(eok).append("억");
        }

        // 만 단위 처리
        if (man > 0) {
            if (!result.isEmpty()) result.append(" "); // 앞에 억이 있으면 공백 추가
            result.append(man).append("만");
        }

        return result.toString();
    }

    public static String convertMarkerPrice(String input) {
        int price = Integer.parseInt(input.replace(",", ""));

        if (price >= 10000) { // 억 단위로 변환
            double converted = price / 10000.0;
            return String.format("%.1f억", converted);
        } else if (price >= 1000) { // 천 단위로 변환
            double converted = price / 1000.0;
            return String.format("%.1f천", converted);
        } else { // 1000 미만의 경우 그대로 출력
            return price + "만";
        }
    }

    private static final double SQUARE_METER_TO_PYEONG = 1 / 3.305785;

    public static String convertToPyeong(String squareMeters) {
        double squareMeter = Double.parseDouble(squareMeters);
        double pyeong = squareMeter * SQUARE_METER_TO_PYEONG;
        return String.format("%.1f평", pyeong);
    }
}
