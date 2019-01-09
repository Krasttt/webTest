package com.project.Sevices;

import com.project.domain.Answer;
import com.project.domain.Question;
import com.project.domain.Test;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import com.project.repositories.TestRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditTestService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TestRepository testRepository;

    public List<Answer> editQuestionAnswers(Integer id, List<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.getText().equals("")) {
                answer.setText(answerRepository.findById(answer.getId()).getText());
            }
            answer.setQuestion(questionRepository.findById(id));
        }
        answerRepository.saveAll(answers);
        return answers;
    }

    public List<Answer> getAnswers(List<Question> questions) {
        List<Answer> answers = new ArrayList<>();

        for (Question q : questions) {
            answers.addAll(answerRepository.findByQuestionId(q.getId()));
        }
        return answers;
    }

    public List<Question> getQuestions(Integer id) {
        return questionRepository.findByTestId(id);
    }

    public void editQuestionText(Integer id, String questionText) {
        Question question = questionRepository.findById(id);
        question.setText(questionText);
        questionRepository.save(question);
    }

    public void deleteQuestion(Integer testId,Integer questionId)  {
        if (questionRepository.findById(questionId)==null) {
            return;
        }
        List<Answer> answers =answerRepository.findByQuestionId(questionId);
        for (Answer answer:answers) {
            answerRepository.delete(answer);
        }
        questionRepository.delete(questionRepository.findById((questionId)));
        Test test = testRepository.findById(testId);
        test.setAmountQuestions(test.getAmountQuestions()-1);
        testRepository.save(test);
    }
}
