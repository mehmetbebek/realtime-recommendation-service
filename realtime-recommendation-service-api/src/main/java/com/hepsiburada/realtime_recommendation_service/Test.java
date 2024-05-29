package com.hepsiburada.realtime_recommendation_service;

import org.springframework.util.StringUtils;

public class Test {
    public static void main(String[] args) {
        String[] messages = new String[4];
        messages[0] = "*====#";
        messages[1] = "X-+-+-+-Z";
        messages[2] = "#______X";
        messages[3] = "A......*";

        System.out.println(rebuildMessage(messages));
    }

    public static String rebuildMessage(String[] messages) {
        String finalMessage = "";

        String messageStartingWithA = "";

        for (int i = 0; i < messages.length; i++) {
            if (i == 0) {
                finalMessage = messages[0];
            } else {
                String nextMessage = findNextMessage(messages, messages[i - 1]);
                if (StringUtils.hasText(nextMessage)) {
                    nextMessage = nextMessage.substring(1, nextMessage.length() - 1);
                }

                finalMessage = finalMessage + nextMessage;
            }

            if (messages[i].startsWith("A") && i != 0) {
                messageStartingWithA = messages[i];
                messageStartingWithA = messageStartingWithA.substring(0, messageStartingWithA.length() - 2);
            }
        }

        return messageStartingWithA + finalMessage;
    }

    public static String findNextMessage(String[] messages, String desiredMessage) {

        char lastCharacterOfMessage = desiredMessage.charAt(desiredMessage.length() - 1);


        for (String message : messages) {
            char firstCharacterOfMessage = message.charAt(0);

            if (lastCharacterOfMessage == firstCharacterOfMessage) {
                return message;
            }
        }

        return "";
    }
}
