package com.project.Sevices;

import com.project.domain.*;
import com.project.repositories.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class TestService {
    private final ResultRepository resultRepository;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final ResultQuestionRepository resultQuestionRepository;

    public TestService(ResultRepository resultRepository, TestRepository testRepository,
                       QuestionRepository questionRepository, AnswerRepository answerRepository,
                       UserAnswerRepository userAnswerRepository, ResultQuestionRepository resultQuestionRepository) {
        this.resultRepository = resultRepository;
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.resultQuestionRepository = resultQuestionRepository;
    }


    public Result createResult(Integer id, UserAccount user) {
        Result result = new Result(new Date(), testRepository.findById(id).getName(),
                testRepository.findById(id), user, 2,true);
        return resultRepository.save(result);
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
        Double countRightAnswers=0.0;
        Integer amountQuestions = questionRepository.findByTestId(testId).size();
        for (ResultQuestion question:questions) {
            if (question.isCorrectness()){
                countRightAnswers++;
            }
        }
        map.put("countRightAnswers", countRightAnswers.intValue());
        map.put("amountQuestions", amountQuestions);

        Result result = resultRepository.findById(resultId);
        result.setGrade(countRightAnswers / amountQuestions);
        result.setActive(false);
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

    public Result getActiveResult(Integer testId) {
        Result result = resultRepository.findFirstByTestIdAndActive(testId,true);
        return result;
    }

    public List<Result> getAllActiveResult(boolean active) {
        return resultRepository.findByActive(active);
    }
}
