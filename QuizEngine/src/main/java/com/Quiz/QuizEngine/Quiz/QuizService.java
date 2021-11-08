package com.Quiz.QuizEngine.Quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public void addQuestion(Question question) {
        Optional<Question> optionalQuestion =  quizRepository.findQuestionByText(question.getText());
//To można do @Component
        if(optionalQuestion.isPresent()) {
            throw new IllegalStateException("There is a similar question already saved id:" + optionalQuestion.get().getId());
        }
        optionalQuestion = quizRepository.findById(question.getId());
        if(optionalQuestion.isPresent()){
            throw new IllegalStateException("There is a question with that id");
        }
        quizRepository.save(question);
    }

    public List<Question> getQuestions (String password) {
        if (password.equals("buba")) {
            return quizRepository.findAll();
        } else {
            throw new IllegalStateException("wrong password");
        }
    }

    public List<String> getQuestion(Integer id) {
        Optional<Question> optionalQuestion = quizRepository.findById(id);

        if(!optionalQuestion.isPresent()){
            throw new IllegalStateException("There is no question with that id");
        } else {
            return Stream.of(optionalQuestion.get().getText(),optionalQuestion.get().getAnswers()).collect(Collectors.toList());
        }
    }

    public String checkAnswer(Integer id, String answer) {
        quizRepository.findById(id).orElseThrow(() -> new IllegalStateException("There is no question with that id"));

        if( quizRepository.findById(id).get().getRightAnswer().equals(answer)) {
            return "Dobra odpowiedź";
        } else {
            return "Zła odpowiedź";
        }
    }
    @Transactional
    public void changeQuestion(Integer id, String text, String answers, String rightAnswer) {

        Question question = quizRepository.findById(id).orElseThrow(() -> new IllegalStateException("There is no question with that id"));

        if(quizRepository.findQuestionByText(text).isPresent()){
            throw new IllegalStateException("There is a similar question already");
        }

        if(answers != null && !Objects.equals(answers, question.getAnswers()) && answers.length() > 0){
            question.setAnswers(answers);
        }

        if(rightAnswer != null && !Objects.equals(rightAnswer, question.getRightAnswer()) && rightAnswer.length() > 0){
            question.setRightAnswer(rightAnswer);
        }

        if(text != null && rightAnswer.length() > 0){
            question.setRightAnswer(rightAnswer);
        }
    }
}
