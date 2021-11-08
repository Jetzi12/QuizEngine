package com.Quiz.QuizEngine.Quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Builder
@Getter
@Setter
public class Question {
    @Id
    @SequenceGenerator(
            name = "Quiz_Sequence",
            sequenceName = "Quiz_Sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Quiz_Sequence"
    )
    private Integer id;
    private String text;
    private String answers;
    private String rightAnswer;
    private LocalDate dateOfCreate;

    public Question(Integer id, String text, String answers, String rightAnswer, LocalDate dateOfCreate) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.dateOfCreate = dateOfCreate;
    }

    public Question(Integer id, String text, String answers, String rightAnswer) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.dateOfCreate = LocalDate.now();
    }

    public Question() {
    }


}
