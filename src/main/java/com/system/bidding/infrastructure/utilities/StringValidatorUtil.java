package com.system.bidding.infrastructure.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringValidatorUtil {

    /**
     * @param text : String text to test
     * @return true for emptiness
     */
    public static boolean isEmpty(String text) {
        return Objects.isNull(text) || text.isEmpty();
    }

    /**
     * @param text : String text to test
     * @return true : containing reserved word
     */
    @SneakyThrows
    public static boolean isContainingReservedWord(String text) {

        final JsonLoader.Keyword keyword = new ObjectMapper()
                .readValue(JsonLoader.Keyword.class.getResourceAsStream("/json/reserve-words.json")
                        , JsonLoader.Keyword.class
                );
        return keyword.getJavaKeywords().contains(text) ||
                keyword.getSqlKeywords().contains(text.toUpperCase());
    }

    /**
     * @param text : String value to test
     * @return true : containing emoji
     */
    public static boolean isContainingEmoji(String text) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .toList()
                .stream()
                .anyMatch(StringValidatorUtil::isEmojiCharacter);
    }

    /**
     * @param text : String value to test
     * @return true : is containing special symbol exist
     */
    public static boolean isContainingSpecialSymbol(String text) {
        return !Pattern.compile("^([a-zA-Z0-9.\\s_-])*$").matcher(text).find();
    }

    public static boolean isLongerThan(int lengthAllowance, String text) {
        return text.length() > lengthAllowance;
    }

    /**
     * @param text : String value to test
     * @return true - for valid email, false - for none email pattern
     */
    public static boolean isInValidEmailPattern(String text) {
        return Objects.isNull(text) || !Pattern.compile("^(.+)@(\\S+)$").matcher(text).find();
    }

    /**
     * a
     *
     * @param codePoint character codepoint
     * @return true : is emoji exist
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return !(codePoint == 0x0
                || codePoint == 0x9
                || codePoint == 0xA
                || codePoint == 0xD
                || codePoint >= 0x20 && codePoint <= 0xD7FF
                || codePoint >= 0xE000 && codePoint <= 0xFFFD
        );
    }


}
