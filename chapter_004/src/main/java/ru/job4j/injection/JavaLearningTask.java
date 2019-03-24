package ru.job4j.injection;

import java.util.Random;

public class JavaLearningTask implements Task {

    private Interview interview;
    private String[] tasks = {"Read Spring doc", "Make Spring bean demo", "watch udemy lessons"};
    public JavaLearningTask(Interview interview) {
        this.interview = interview;
    }
    @Override
    public String getDailyTask() {
        Random r = new Random();
        return tasks[r.nextInt(tasks.length)];
    }

    @Override
    public String getQuestion() {
        return interview.getInterviewQuestion();
    }
}
