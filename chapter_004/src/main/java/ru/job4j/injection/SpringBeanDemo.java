package ru.job4j.injection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanDemo {
    public static void main(String[] args) {
        // read Spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ClassPathXmlApplicationContext annotationContext
                = new ClassPathXmlApplicationContext("annotation-spring-context.xml");

        // Constructor injection
        // retrieve bean from spring container
        Task javaLearner = context.getBean("java", Task.class);
        System.out.println("Java learner task: " + javaLearner.getDailyTask());
        System.out.println("Java learner interview question: " + javaLearner.getQuestion());

        // setter injection
        Task pythonLearner = context.getBean("english", Task.class);
        System.out.println("Python learner task: " + pythonLearner.getDailyTask());
        System.out.println("Python learner interview question: " + pythonLearner.getQuestion());


        // constructor injection with annotation
        Task rubyLearner = annotationContext.getBean("rubyLearningTask", Task.class);
        System.out.println("Ruby learner task: " + rubyLearner.getDailyTask());
        System.out.println("Ruby learner question: " + rubyLearner.getQuestion());


        // close context
        context.close();
        annotationContext.close();
    }



}
