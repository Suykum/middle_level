package ru.job4j.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RubyLearningTask implements Task {

    private Interview interview;

    @Autowired
    public RubyLearningTask(Interview interview) {
        this.interview = interview;
    }
    @Override
    public String getDailyTask() {
        return "Read Ruby books";
    }

    @Override
    public String getQuestion() {
        return interview.getInterviewQuestion();
    }
}
