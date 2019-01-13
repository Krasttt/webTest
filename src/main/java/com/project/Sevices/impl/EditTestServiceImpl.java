package com.project.Sevices.impl;

import com.project.Sevices.EditTestService;
import com.project.domain.Answer;
import com.project.domain.Question;
import com.project.domain.Test;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import com.project.repositories.TestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditTestServiceImpl implements EditTestService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    public EditTestServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository, TestRepository testRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
    }

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
        if (questionText.equals("")||questionText.length()<5) {
            return;
        }
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
