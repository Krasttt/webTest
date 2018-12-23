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

    public void confirmAnswer(Integer id, Integer resultId, List<Answer> answers, UserAccount user) {
        Type typeQuestion = questionRepository.findById(id).getType();
        for (Answer answer : answers) {
            if (typeQuestion != Type.WORD) {
                if (answerRepository.findById(answer.getId()).isCorrect()) {
                    userAnswerRepository.save(new UserAnswer(answer.getText(), true,
                            questionRepository.findById(id), resultRepository.findById(resultId), user));
                } else
                    userAnswerRepository.save(new UserAnswer(answer.getText(), false,
                            questionRepository.findById(id), resultRepository.findById(resultId), user));

            } else userAnswerRepository.save(new UserAnswer(answer.getText(),
                    answer.getText().equals(answerRepository.findById(answer.getId()).getText()),
                    questionRepository.findById(id), resultRepository.findById(resultId), user));
        }
    }

    public Map<String, Integer> setResult(Integer testId, Integer resultId) {
        Map<String, Integer> map = new HashMap<>();
        List<Question> questions = questionRepository.findByTestId(testId);
        int countRightAnswers = 0;
        int amountQuestions = questions.size();
        for (Question question : questions) {

            List<UserAnswer> userAnswers = userAnswerRepository.findByQuestionIdAndResultId(question.getId(), resultId);
            if (question.getType() == Type.MULTI) {
                List<Answer> answers = answerRepository.findByQuestionId(question.getId());
                int countCorrectAnswers = 0;
                int countCorrectUserAnswers = 0;
                for (Answer answer : answers) {
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
                    countRightAnswers++;
                }
            } else {
                for (UserAnswer userAnswer : userAnswers) {

                    if (userAnswer.isCorrect()) {
                        countRightAnswers++;
                    }
                }
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
}
