package repository.dao;

import model.Questions;

import java.sql.SQLException;
import java.util.List;

public interface QuestionRepository {
    Questions get(int id);

    void save(Questions questions);
    void update(Questions questions);
    void delete(int id);
    List<Questions> getAllQuestions() throws SQLException;

    List<Questions> getByTopic(String topic);
}
