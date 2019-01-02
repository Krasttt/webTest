package com.project.Sevices;

import com.project.domain.*;
import com.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class TestService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private ResultQuestionRepository resultQuestionRepository;


    public Result createResult(Integer id, UserAccount user) {
        return resultRepository.save(new Result(new Date(), testRepository.findById(id).getName(),
                testRepository.findById(id), user, 2));
    }

    public Test getTest(Integer id) {
        return testRepository.findById(id);
    }

    public List<Question> getQuestions(Integer id) {
        return questionRepository.findByTestId(id);
    }

    public List<Answer> getAnswers(List<Question> questions) {
        List<Answer> answers = new ArrayList<>();
        for (Question q : questions) {
            answers.addAll(answerRepository.findByQuestionId(q.getId()));
        }
        List<Answer> allAnswers = new ArrayList<>();
        while (!answers.isEmpty()) {
            int index = new Random().nextInt(answers.size());
            allAnswers.add(answers.get(index));
            answers.remove(index);
        }
        return allAnswers;
    }

    public void confirmAnswer(Integer questionId, Integer resultId, List<Answer> answers, UserAccount user) {
        Question question  = questionRepository.findById(questionId);
        Type typeQuestion = question.getType();
        ResultQuestion resultQuestion = new ResultQuestion(question.getText(),typeQuestion,resultRepository.findById(resultId)
        ,false);
        resultQuestionRepository.save(resultQuestion);
        for (Answer answer : answers) {
            if (typeQuestion != Type.WORD) {
                if (answerRepository.findById(answer.getId()).isCorrect()) {
                    userAnswerRepository.save(new UserAnswer(answer.getText(), true,
                            resultQuestion, resultRepository.findById(resultId), user));
                } else
                    userAnswerRepository.save(new UserAnswer(answer.getText(), false,
                            resultQuestion, resultRepository.findById(resultId), user));

            } else userAnswerRepository.save(new UserAnswer(answer.getText(),
                    answer.getText().equals(answerRepository.findById(answer.getId()).getText()),
                    resultQuestion, resultRepository.findById(resultId), user));
        }
        resultQuestionRepository.save(resultQuestion);
        List<UserAnswer> userAnswers = userAnswerRepository.findByResultQuestionIdAndResultId(resultQuestion.getId(), resultId);
        if (question.getType() == Type.MULTI) {
            List<Answer> answersDB = answerRepository.findByQuestionId(questionId);
            int countCorrectAnswers = 0;
            int countCorrectUserAnswers = 0;
            for (Answer answer : answersDB) {
                if (answer.isCorrect()) {
                    countCorrectAnswers++;
                }
            }
            for (UserAnswer userAnswer : userAnswers) {
                if (userAnswer.isCorrect()) {
                    countCorrectUserAnswers++;
                } else {
                    countCorrectUserAnswers -= 100;
                }
            }
            if (countCorrectAnswers != 0 && countCorrectUserAnswers == countCorrectAnswers) {
                resultQuestion.setCorrectness(true);
            }
        } else {
            for (UserAnswer userAnswer : userAnswers) {

                if (userAnswer.isCorrect()) {
                   resultQuestion.setCorrectness(true);
                }
            }
        }
        resultQuestionRepository.save(resultQuestion);
    }

    public Map<String, Integer> setResult(Integer testId, Integer resultId) {
        Map<String, Integer> map = new HashMap<>();
        List<ResultQuestion> questions =resultQuestionRepository.findByResultId(resultId);
        int countRightAnswers=0;
        int amountQuestions = questionRepository.findByTestId(testId).size();
        for (ResultQuestion question:questions) {
            if (question.isCorrectness()){
                countRightAnswers++;
            }
        }
        map.put("countRightAnswers", countRightAnswers);
        map.put("amountQuestions", amountQuestions);

        Result result = resultRepository.findById(resultId);
        result.setGrade(countRightAnswers / amountQuestions);
        result.setDuration(Duration.ofSeconds(new Date().getSeconds() - result.getStartTest().getSeconds()));
        resultRepository.save(result);
        return map;
    }

    public Result getResult(Integer id) {
        return resultRepository.findById(id);
    }

    public List<ResultQuestion> getResultQuestions(Integer resultId) {

        return resultQuestionRepository.findByResultId(resultId);
    }
}
