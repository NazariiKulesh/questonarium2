package service;

import model.Questions;
import repository.dao.QuestionRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionService {
    private final QuestionRepository repository;
    private final Map<String, List<Questions>> questionsByTopic = new HashMap<>();

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Questions getRndQuestionByTopic(String topic) {
        List<Questions> questions=questionsByTopic.containsKey(topic)?questionsByTopic.get(topic):repository.getByTopic(topic);
        questionsByTopic.put(topic, questions);
        int rndNum = ThreadLocalRandom.current().nextInt(0, questions.size());
        return questions.get(rndNum);
    }

    public Questions getRndQuestions() throws SQLException {
        List<Questions> topics = repository.getAllQuestions();
        int rndNum = ThreadLocalRandom.current().nextInt(0, topics.size());
        return topics.get(rndNum);

    }


}
