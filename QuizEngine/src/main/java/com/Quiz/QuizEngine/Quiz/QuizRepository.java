package com.Quiz.QuizEngine.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT s FROM Question s WHERE s.text = ?1")
    Optional<Question> findQuestionByText (String text);

}

