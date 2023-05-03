package repository;

import model.Questions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class QuestionRepositoryTest {
    private static Connection connection;
    private QuestionRepository impl;
    private int testId = 1000;
    private String testText = "Text Question";
    private String testTopic = "Topic test";
    private Questions questionToSave = Questions.builder()
            .id(testId)
            .text(testText)
            .topic(testTopic)
            .build();

    @BeforeClass
    public static void createdConnection() throws SQLException {
        ConnectionSingelton.getConnection();
    }

    @Before
    public void saveTestEntity() {
        this.impl = new QuestionRepositoryImpl(connection);
        this.impl.save(questionToSave);
    }

    @Test
    public void getTest() {
        Questions actual = this.impl.get(testId);
        this.impl.delete(testId);
        Assert.assertEquals(questionToSave, actual);
    }

    @Test
    public void saveTest() {
        Questions actual = this.impl.get(testId);
        Assert.assertEquals(questionToSave, actual);
    }

    @Test
    public void updateTest() {
        String updateTest = "Update questions";
        Questions questionsToUpdate = Questions.builder()
                .id(testId)
                .text(testText)
                .topic(testTopic)
                .build();
        this.impl.update(questionsToUpdate);
        Questions actual = this.impl.get(testId);
        this.impl.delete(testId);
        Assert.assertEquals(questionsToUpdate, actual);
    }

    @Test
    public void deleteTest() {
        this.impl.delete(testId);
        int actual = this.impl.getByTopic(this.testTopic).size();
        Assert.assertEquals(0, actual);
    }
    @Test
    public void getByTopicTest(){
        List<Questions> actual = this.impl.getByTopic(testTopic);
        List<Questions> expected = List.of(questionToSave);
        this.impl.delete(testId);
        Assert.assertEquals(expected,actual);
    }


}
