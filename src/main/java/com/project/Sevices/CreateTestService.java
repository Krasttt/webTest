package com.project.Sevices;

import com.project.domain.*;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import com.project.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateTestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public void addTest(String name, String description, Integer duration, UserAccount user) {
        testRepository.save(new Test(name, description, 0, Duration.ofMinutes(duration), user));
    }

    public void addMultiQuestion(String textQuestion, String id, String check1, String check2, String check3, String check4,
                                 String answer1, String answer2, String answer3, String answer4) {
        Test test = testRepository.findById(Integer.parseInt(id));
        test.setAmountQuestions(test.getAmountQuestions() + 1);
        testRepository.save(test);

        Question question = new Question(textQuestion, Type.MULTI, test);
        questionRepository.save(question);

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(answer1, check1.equals("true"), question));
        answers.add(new Answer(answer2, check2.equals("true"), question));
        answers.add(new Answer(answer3, check3.equals("true"), question));
        answers.add(new Answer(answer4, check4.equals("true"), question));
        answerRepository.saveAll(answers);
    }

    public void addSingleQuestion(String textQuestion, String corAnswer, String answer2,
                                  String answer3, String answer4, String id) {
        Test test = testRepository.findById(Integer.parseInt(id));
        test.setAmountQuestions(test.getAmountQuestions() + 1);
        testRepository.save(test);
        Question question = new Question(textQuestion, Type.SINGLE, test);
        questionRepository.save(question);

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(corAnswer, true, question));
        answers.add(new Answer(answer2, false, question));
        answers.add(new Answer(answer3, false, question));
        answers.add(new Answer(answer4, false, question));
        answerRepository.saveAll(answers);
    }

    public void addWordQuestion(String textQuestion, String answer, String id) {
        Test test = testRepository.findById(Integer.parseInt(id));
        test.setAmountQuestions(test.getAmountQuestions() + 1);
        testRepository.save(test);

        Question question = new Question(textQuestion, Type.WORD, test);
        questionRepository.save(question);

        answerRepository.save(new Answer(answer, true, question));
    }
}
