package com.project;


import com.project.Sevices.CreateTestService;
import com.project.domain.Answer;
import com.project.domain.Question;
import com.project.domain.Test;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import com.project.repositories.TestRepository;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CreateTestServiceTest {
    @Autowired
    private CreateTestService createTestService;

    @MockBean
    private TestRepository testRepository;
    @MockBean
    private QuestionRepository questionRepository;
    @MockBean
    private AnswerRepository answerRepository;

    @org.junit.Test
    public void addMultiQuestionTest(){

        Test test = new Test();
        test.setId(1);
        test.setAmountQuestions(0);

        Mockito.doReturn(test)
                .when(testRepository)
                .findById(1);

        createTestService.addMultiQuestion("textQuestion",test.getId().toString(),"false","false",
                "true","true","answer1","answer2","answer3","answer4");

        Assert.assertTrue(test.getAmountQuestions()==1);
        Mockito.verify(testRepository,Mockito.times(1)).save(test);
        Mockito.verify(questionRepository,Mockito.times(1)).save(ArgumentMatchers.any(Question.class));
        Mockito.verify(answerRepository,Mockito.times(1)).saveAll(ArgumentMatchers.any(ArrayList.class));
    }

    @org.junit.Test
    public void addSingleQuestionTest(){
        Test test = new Test();
        test.setId(1);
        test.setAmountQuestions(0);

        Mockito.doReturn(test)
                .when(testRepository)
                .findById(1);

        createTestService.addSingleQuestion("textQuestion","corAnswer","answer2",
                "answer3","answer4",test.getId().toString());

        Assert.assertTrue(test.getAmountQuestions()==1);
        Mockito.verify(testRepository,Mockito.times(1)).save(test);
        Mockito.verify(questionRepository,Mockito.times(1)).save(ArgumentMatchers.any(Question.class));
        Mockito.verify(answerRepository,Mockito.times(1)).saveAll(ArgumentMatchers.any(ArrayList.class));
    }
    @org.junit.Test
    public void addWordQuestionTest() {
        Test test = new Test();
        test.setId(1);
        test.setAmountQuestions(0);

        Mockito.doReturn(test)
                .when(testRepository)
                .findById(1);

        createTestService.addWordQuestion("textQuestion","corAnswer",test.getId().toString());

        Assert.assertTrue(test.getAmountQuestions()==1);
        Mockito.verify(testRepository,Mockito.times(1)).save(test);
        Mockito.verify(questionRepository,Mockito.times(1)).save(ArgumentMatchers.any(Question.class));
        Mockito.verify(answerRepository,Mockito.times(1)).save(ArgumentMatchers.any(Answer.class));
    }

}
