package com.occydaboss.skyblock.util;

public class TextCase
{
    public static String setCase (String str)
    {
        String firstLetter = str.substring(0, 1);
        String remainingLetters = str.substring(1, str.length()).toLowerCase();

        return firstLetter + remainingLetters;
    }
}
