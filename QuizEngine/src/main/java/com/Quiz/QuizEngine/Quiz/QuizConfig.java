package com.Quiz.QuizEngine.Quiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class QuizConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(
                        new User("Michal",
                                passwordEncoder().encode("Michael54"),
                                Collections.singleton(new SimpleGrantedAuthority("user"))));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/showAll")
                .authenticated()
                .and()
                .formLogin()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner commandLineRunner(QuizRepository quizRepository){
        return args -> {
            Question exampleQuestion = Question.builder()
                    .answers("A: AnswerA, B: AnswerB, C: AnswerC")
                    .rightAnswer("B: AnswerB")
                    .dateOfCreate(LocalDate.now())
                    .id(0)
                    .text( "Text of the question").build();
            quizRepository.saveAll(Stream.of(exampleQuestion).collect(Collectors.toList()));
        };
    }
}
