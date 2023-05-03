package org.example;

import model.Questions;
import repository.ConnectionSingelton;
import repository.QuestionRepositoryImpl;
import service.QuestionService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl(ConnectionSingelton.getConnection()));
        System.out.println("Do u want a random questions by topic?");
        while (scanner.next().equals("yes")){
            System.out.println("Topic name");
            System.out.println(questionService.getRndQuestionByTopic(scanner.next()).getText());
            System.out.println("Do u want a next question?");

        }
    }
}