package ru.job4j.injection;

import java.util.Random;

public class PythonLearningTask implements Task {

    private Interview interview;
    private String[] tasks = {"Read collections", "Write demo app using interfaces", "Learn Panda tools"};
    @Override
    public String getDailyTask() {
        Random r = new Random();
        return tasks[r.nextInt(tasks.length)];
    }

    @Override
    public String getQuestion() {
        return interview.getInterviewQuestion();
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }
}
