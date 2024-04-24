//package com.api.emotibook.controller;
//
//import edu.stanford.nlp.pipeline.Annotation;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.sentiment.SentimentClass;
//import edu.stanford.nlp.util.CoreMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//public class SentimentAnalysisService {
//
//    @Autowired
//    private StanfordCoreNLP pipeline;
//
//    @GetMapping("/analyze")
//    public SentimentResponse analyzeSentiment(@RequestParam String text) throws IOException {
//
//        if (pipeline == null) {
//            // Handle cases where pipeline initialization fails
//            throw new RuntimeException("Failed to initialize Stanford CoreNLP pipeline");
//        }
//
//        Annotation annotation = new Annotation(text);
//        pipeline.annotate(annotation);
//
//        double sentimentScore = 0.0;
//        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
//
//        for (CoreMap sentence : sentences) {
//            sentimentScore += sentence.get(SentimentClass.class).ordinal();
//        }
//
//        // Adjust sentiment score based on sentence count or desired logic
//        sentimentScore = sentimentScore / sentences.size();
//
//        return new SentimentResponse(sentimentScore);
//    }
//
//    @PostConstruct
//    public void init() throws IOException {
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize, ssplit, pos, sentiment");
//
//        // You might need to adjust the path to the Stanford CoreNLP resources directory
//        props.setProperty("StanfordCoreNLP.resources", "/path/to/stanford-corenlp-resources-X.X.X");
//        pipeline = new StanfordCoreNLP(props);
//    }
//
//    public static class SentimentResponse {
//        private final double score;
//
//        public SentimentResponse(double score) {
//            this.score = score;
//        }
//
//        public double getScore() {
//            return score;
//        }
//    }
//}
