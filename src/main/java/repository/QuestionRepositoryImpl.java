package repository;

import model.Questions;
import repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final Connection connection;


    public QuestionRepositoryImpl(Connection connection) {
        this.connection = ConnectionSingelton.getConnection();
    }

    private final String findById = "SELECT * FROM questions where id=?";
    private final String getAllQst = "SELECT * FROM questions";
    private final String saveQuestions = "INSERT INTO questions VALUE(?,?,?)";
    private final String findByTopic = "SELECT * FROM questions where topic=?";

    private final String updateText = "UPDATE question SET text=? where id=?";

    private final String deleteById = "DELETE FROM questions where id=?";

    @Override
    public Questions get(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return Questions.builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .topic(resultSet.getString("topic"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Questions questions) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(saveQuestions);
            preparedStatement.setInt(1, questions.getId());
            preparedStatement.setString(2, questions.getText());
            preparedStatement.setString(3, questions.getTopic());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void update(Questions questions) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(updateText);
            preparedStatement.setString(1, questions.getText());
            preparedStatement.setInt(2, questions.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(deleteById);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Questions> getByTopic(String topic) {
        List<Questions> questionsByTopic = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByTopic);
            preparedStatement.setString(1,topic);
            ResultSet questions = preparedStatement.executeQuery();
            while (questions.next()){
                questionsByTopic.add(Questions.builder()
                        .id(questions.getInt("id"))
                        .text(questions.getString("text"))
                        .topic(questions.getString("topic"))
                        .build());
            }
            return questionsByTopic;
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }
    public List<Questions> getAllQuestions() throws SQLException {
        List<Questions> allQuestions = new ArrayList<>();
            PreparedStatement preparedStatement = this.connection.prepareStatement(getAllQst);
            ResultSet questions = preparedStatement.executeQuery();
        while (questions.next()){
            allQuestions.add(Questions.builder()
                    .id(questions.getInt("id"))
                    .text(questions.getString("text"))
                    .topic(questions.getString("topic"))
                    .build());


    }
        return allQuestions;
}
}