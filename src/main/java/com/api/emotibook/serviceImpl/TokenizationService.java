package com.api.emotibook.serviceImpl;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class TokenizationService {

    public String tokenizeText(String text) {
        // Tokenize the input text using StringTokenizer
        StringTokenizer tokenizer = new StringTokenizer(text);

        // Initialize counts for positive, neutral, and negative tokens
        int positiveCount = 0;
        int neutralCount = 0;
        int negativeCount = 0;

        // Iterate through each token and categorize them
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().toLowerCase(); // Convert token to lowercase for case-insensitive comparison
            // Here you can implement your logic to determine if a token is positive, neutral, or negative
            // For simplicity, let's assume positive tokens contain the word "good" and negative tokens contain the word "bad"
            List<String> goodWords;
            List<String> badWords;

            try {
                 goodWords = tokenize("good");
                 badWords = tokenize("bad");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (goodWords.contains(token)) {
                positiveCount++;
            } else if (badWords.contains(token)) {
                negativeCount++;
            } else {
                neutralCount++;
            }
        }

        // Calculate the ratios
        double totalTokens = positiveCount + neutralCount + negativeCount;
        double positiveRatio = positiveCount / totalTokens;
        double negativeRatio = negativeCount / totalTokens;
        double neutralRatio = neutralCount / (totalTokens * 10);

        // Create and return the response object
        return (positiveRatio > negativeRatio ? (positiveRatio > neutralRatio) ? "POSITIVE" : "NEUTRAL" : (negativeRatio > neutralRatio) ? "NEGATIVE" : "NEUTRAL" );
    }

    public List<String> tokenize(String type) throws IOException {
        List<String> goodTokens = new ArrayList<>();

        goodTokens.add("happy");
        goodTokens.add("joyful");
        goodTokens.add("excellent");
        goodTokens.add("awesome");
        goodTokens.add("positive");
        goodTokens.add("wonderful");
        goodTokens.add("fantastic");
        goodTokens.add("great");
        goodTokens.add("amazing");
        goodTokens.add("brilliant");
        goodTokens.add("delightful");
        goodTokens.add("lovely");
        goodTokens.add("splendid");
        goodTokens.add("superb");
        goodTokens.add("terrific");
        goodTokens.add("glorious");
        goodTokens.add("fabulous");
        goodTokens.add("marvelous");
        goodTokens.add("spectacular");
        goodTokens.add("beautiful");
        goodTokens.add("kind");
        goodTokens.add("friendly");
        goodTokens.add("awesome");
        goodTokens.add("outstanding");
        goodTokens.add("perfect");
        goodTokens.add("praiseworthy");
        goodTokens.add("admired");
        goodTokens.add("appreciated");
        goodTokens.add("celebrated");
        goodTokens.add("cheerful");
        goodTokens.add("heartwarming");

        List<String> badTokens = new ArrayList<>();
        badTokens.add("sad");
        badTokens.add("unhappy");
        badTokens.add("terrible");
        badTokens.add("awful");
        badTokens.add("negative");
        badTokens.add("horrible");
        badTokens.add("disappointing");
        badTokens.add("disgusting");
        badTokens.add("dreadful");
        badTokens.add("miserable");
        badTokens.add("unpleasant");
        badTokens.add("tragic");
        badTokens.add("gloomy");
        badTokens.add("depressing");
        badTokens.add("disheartening");
        badTokens.add("abysmal");
        badTokens.add("distressing");
        badTokens.add("heartbreaking");
        badTokens.add("unfortunate");
        badTokens.add("shameful");
        badTokens.add("unsatisfactory");
        badTokens.add("bad");
        badTokens.add("ugly");
        badTokens.add("disliked");
        badTokens.add("hated");
        badTokens.add("abominable");
        badTokens.add("repulsive");
        badTokens.add("detestable");
        badTokens.add("repugnant");
        badTokens.add("revolting");
        badTokens.add("loathsome");

        if (type.equals("good")) {
            return goodTokens;
        }
        else {
           return badTokens;
        }

    }
}
