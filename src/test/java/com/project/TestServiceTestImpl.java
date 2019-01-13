package com.project;

import com.project.Sevices.impl.TestServiceImpl;
import com.project.domain.*;
import com.project.repositories.*;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestServiceTestImpl {
    @Autowired
    private TestServiceImpl testServiceImpl;

    @MockBean
    private ResultRepository resultRepository;
    @MockBean
    private TestRepository testRepository;
    @MockBean
    private QuestionRepository questionRepository;
    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private UserAnswerRepository userAnswerRepository;
    @MockBean
    private ResultQuestionRepository resultQuestionRepository;

    @org.junit.Test
    public void confirmAnswerTest() {
        Question question = new Question();
        question.setId(1);
        question.setType(Type.SINGLE);

        Mockito.doReturn(question)
                .when(questionRepository)
                .findById(question.getId());

        ResultQuestion resultQuestion = new ResultQuestion();
        Result result = new Result();
        result.setId(1);
        resultQuestion.setResult(result);

        Mockito.doReturn(result)
                .when(resultRepository)
                .findById(resultQuestion.getResult().getId());

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("text", true, null));
        answers.add(new Answer("text", true, null));
        answers.add(new Answer("text", false, null));
        answers.add(new Answer("text", false, null));
        for (int i = 0; i < answers.size(); i++) {
            answers.get(i).setId(i + 1);
        }

        Mockito.doReturn(answers.get(0))
                .when(answerRepository)
                .findById(1);

        Mockito.doReturn(answers.get(1))
                .when(answerRepository)
                .findById(2);

        Mockito.doReturn(answers.get(2))
                .when(answerRepository)
                .findById(3);

        Mockito.doReturn(answers.get(3))
                .when(answerRepository)
                .findById(4);
    }

    @org.junit.Test
    public void setResultTest() {
        List<ResultQuestion> questions = new ArrayList<>();
        questions.add(new ResultQuestion("text", null, null, true));
        questions.add(new ResultQuestion("text", null, null, true));
        questions.add(new ResultQuestion("text", null, null, false));
        questions.add(new ResultQuestion("text", null, null, false));
        questions.add(new ResultQuestion("text", null, null, true));
        questions.add(new ResultQuestion("text", null, null, true));
        Result result = new Result();
        result.setId(1);
        result.setStartTest(new Date());
        Mockito.doReturn(questions)
                .when(resultQuestionRepository)
                .findByResultId(result.getId());

        Test test = new Test();
        test.setId(1);

        Mockito.doReturn(questions)
                .when(questionRepository)
                .findByTestId(test.getId());

        Mockito.doReturn(result)
                .when(resultRepository)
                .findById(result.getId());
        testServiceImpl.setResult(test.getId(),result.getId());

        Assert.assertTrue(result.getGrade()==4);
        Assert.assertFalse(result.isActive());
        Mockito.verify(resultRepository,Mockito.times(1))
                .save(ArgumentMatchers.any(Result.class));
    }
}
