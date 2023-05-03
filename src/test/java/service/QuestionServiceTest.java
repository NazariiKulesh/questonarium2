package service;

import model.Questions;
import org.junit.Assert;
import org.junit.Test;
import repository.ConnectionSingelton;
import repository.QuestionRepositoryImpl;
import repository.dao.QuestionRepository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class QuestionServiceTest {
    private  List<Questions> testQuestions = List.of(
            Questions.builder().id(1).text("another").topic("another").build(),
            Questions.builder().id(2).text("something").topic("another").build(),
            Questions.builder().id(3).text("error").topic("another").build());

    private QuestionRepository repository = new QuestionRepository(){

        @Override
        public Questions get(int id) {
            return null;
        }

        @Override
        public void save(Questions questions) {

        }

        @Override
        public void update(Questions questions) {

        }

        @Override
        public void delete(int id) {

        }

        @Override
        public List<Questions> getAllQuestions() throws SQLException {
            return testQuestions;
        }

        @Override
        public List<Questions> getByTopic(String topic) {
            return null;
        }
    };

    @Test
    public void getRndQuestionsByTopicTest()throws SQLException{
        String topic = "OOP";
        QuestionRepositoryImpl impl = new QuestionRepositoryImpl(ConnectionSingelton.getConnection());
        QuestionService testService = new QuestionService(impl);
        Questions rndQuestionByTopic = testService.getRndQuestionByTopic(topic);
        Assert.assertTrue(impl.getByTopic(topic).contains(rndQuestionByTopic));
    }

    @Test
    public void getRndQuestionsTest() throws SQLException {
        QuestionService questionService = new QuestionService(this.repository);
        Questions rndQuestions =questionService.getRndQuestions();
        Assert.assertTrue(testQuestions.contains(rndQuestions));


    }
}
