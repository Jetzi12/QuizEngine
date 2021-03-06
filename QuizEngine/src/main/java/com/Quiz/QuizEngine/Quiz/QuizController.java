package com.Quiz.QuizEngine.Quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class QuizController {

    private final QuizService quizService;
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }



    @GetMapping(path = "showAll")//{masterPassword}")
    public List<Question> getQuestions (){//@PathVariable("masterPassword") String password){
        return quizService.getQuestions("buba");//password);
    }
    @GetMapping(path = "{id}")
    public List<String> getQuestion (@PathVariable("id") Integer id){
        return quizService.getQuestion(id);
    }

    @GetMapping(path = "answer/{id}")
    public String answerQuestion (@PathVariable("id") Integer id,
                                  @RequestParam String answer){
        return quizService.checkAnswer(id,answer);
    }

    @PutMapping(path = "changeQuestion/{id}")
    public void changeQuestion (@PathVariable("id") Integer id,
                                @RequestParam(required = false) String text,
                                @RequestParam(required = false) String answer,
                                @RequestParam(required = false) String rightAnswer){
        quizService.changeQuestion(id,text,answer,rightAnswer);

    }

    @PostMapping
    public void addQuestion (@RequestBody Question question){
        quizService.addQuestion(question);
    }

}
