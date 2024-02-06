package com.sse.homeworkSubmit.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinConverter {
    private HanyuPinyinOutputFormat format = PinyinConverter.getOutputFormat();

    private static HanyuPinyinOutputFormat getOutputFormat() {
        // 设置生成拼音的大小写、音调、拼音v
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        return format;
    }

    private static boolean checkHanyu(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    public static String getPinyin(String text, String separator) throws BadHanyuPinyinOutputFormatCombination {
       return PinyinHelper.toHanYuPinyinString(text, PinyinConverter.getOutputFormat(), separator, true);
    }

    public static String getInitialLetter(String text) {
        char[] chars = text.toCharArray();

        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            char letter = Character.toLowerCase(c);
            if(PinyinConverter.checkHanyu(c)) {
                String[] array = PinyinHelper.toHanyuPinyinStringArray(c);
                if(array.length > 0 ) {
                    letter = array[0].charAt(0);
                }
            }

            builder.append(letter);
        }

        return builder.toString();
    }


}
