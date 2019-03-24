package ru.job4j.injection;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class JobInterview implements Interview {
    private String[] questions = {"Introduce yourself", "Explain OOP", "What is functional programming?",
                                    "List collection types"};
    @Override
    public String getInterviewQuestion() {
        Random r = new Random();
        return questions[r.nextInt(questions.length)];
    }
}
