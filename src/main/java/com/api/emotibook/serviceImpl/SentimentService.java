package com.api.emotibook.serviceImpl;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SentimentService {

    private final StanfordCoreNLP pipeline;

    public TokenizationService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public String tokenizeText(String text) {
        // Process text with CoreNLP pipeline
        CoreMap coreMap = new Annotation(text);
        pipeline.annotate(coreMap);

        // Extract sentiment
        String sentiment = coreMap.get(SentimentCoreAnnotations.SentimentClass.class);

        return sentiment.toUpperCase();
    }
}
