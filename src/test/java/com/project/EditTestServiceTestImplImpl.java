package com.project;


import com.project.Sevices.impl.EditTestServiceImpl;
import com.project.domain.Answer;
import com.project.domain.Question;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import com.project.repositories.TestRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EditTestServiceTestImplImpl {
    @Autowired
    private EditTestServiceImpl editTestServiceImpl;

    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private QuestionRepository questionRepository;
    @MockBean
    private TestRepository testRepository;

    @Test
    public void editQuestionTextTest(){
        Question question = new Question();
        question.setId(1);
        question.setText("Text of question");
        Mockito.doReturn(question)
                .when(questionRepository)
                .findById(question.getId());

        editTestServiceImpl.editQuestionText(question.getId(),"New text of question");
        Assert.assertTrue(question.getText().equals("New text of question"));
        Mockito.verify(questionRepository,Mockito.times(1)).save(ArgumentMatchers.any(Question.class));
    }

    @Test
    public void editQuestionAnswersTest(){
        Question question = new Question();
        question.setId(1);

        Mockito.doReturn(question)
                .when(questionRepository)
                .findById(question.getId());

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("answer1",true,null));
        answers.add(new Answer("answer2",false,null));
        answers.add(new Answer("answer3",false,null));
        answers.add(new Answer("answer4",false,null));

        for (int i = 0 ; i <answers.size();i++){
            answers.get(i).setId(i+1);
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

        editTestServiceImpl.editQuestionAnswers(question.getId(),answers);

        Mockito.verify(answerRepository,Mockito.times(1)).saveAll(ArgumentMatchers.anyIterable());
    }

    @Test
    public void deleteQuestionTest(){
        Question question = new Question();
        question.setId(1);

        Mockito.doReturn(question)
                .when(questionRepository)
                .findById(question.getId());

        com.project.domain.Test test = new com.project.domain.Test();
        test.setId(1);
        test.setAmountQuestions(10);
        Mockito.doReturn(test)
                .when(testRepository)
                .findById(test.getId());

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("answer1",true,null));
        answers.add(new Answer("answer2",false,null));
        answers.add(new Answer("answer3",false,null));
        answers.add(new Answer("answer4",false,null));

        Mockito.doReturn(answers)
                .when(answerRepository)
                .findByQuestionId(question.getId());
        editTestServiceImpl.deleteQuestion(test.getId(),question.getId());

        Mockito.verify(answerRepository,Mockito.times(answers.size())).delete(ArgumentMatchers.any(Answer.class));
        Mockito.verify(questionRepository,Mockito.times(1)).delete(ArgumentMatchers.any(Question.class));
        Assert.assertTrue(test.getAmountQuestions()==9);
        Mockito.verify(testRepository,Mockito.times(1)).save(ArgumentMatchers.any(com.project.domain.Test.class));
    }

}
