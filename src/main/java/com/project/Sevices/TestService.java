package com.project.Sevices;

import com.project.domain.*;

import java.util.List;
import java.util.Map;

/**
 * Сервис предназначен для отображения теста,сохранения его результатов и сохранения отдельных ответов на вопросы.
 */
public interface TestService {
    /**
     * Метод сохраняет в БД запись при запуске теста.
     *
     * @param id
     * @param user
     * @return Result
     */
    Result createResult(Integer id, UserAccount user);

    /**
     * Метод возвращает из БД тест по id.
     *
     * @param id
     * @return Test
     */
    Test getTest(Integer id);

    /**
     * Метод возвращает все вопросы, пренадлежащие конкретному тесту.
     *
     * @param id
     * @return List<Question>
     */
    List<Question> getQuestions(Integer id);

    /**
     * Метод возвращает все ответы на вопросы,переданые в параметре метода.
     *
     * @param questions
     * @return List<Answer>
     */
    List<Answer> getAnswers(List<Question> questions);

    /**
     * Метод проверяет на корректность ответ пользователя на вопрос и сохраняет в БД.
     *
     * @param questionId
     * @param resultId
     * @param answers
     * @param user
     */
    void confirmAnswer(Integer questionId, Integer resultId, List<Answer> answers, UserAccount user);

    /**
     * Метод подводит результаты за пройденный тест,т.е. устанавливает оценку, время потраченное на тест и сохраняет в БД.
     * @param testId
     * @param resultId
     * @return Map<String,Integer>
     */
    Map<String, Integer> setResult(Integer testId, Integer resultId);

    /**
     * Метод возвращает из БД результат по id.
     *
     * @param id
     * @return Result
     */
    Result getResult(Integer id);

    /**
     * Метод возвращает из БД вопросы, на которые ответил пользователь, по id.
     * @param resultId
     * @return List<ResultQuestion>
     */
    List<ResultQuestion> getResultQuestions(Integer resultId);

    /**
     * Метод возвращает из БД результаты, еще незавершенных тестов, по id.
     *
     * @param testId
     * @return Result
     */
    Result getActiveResult(Integer testId);

    /**
     * Метод возвращает из БД все активные тесты.
     *
     * @param active
     * @return List<Result>
     */
    List<Result> getAllActiveResult(boolean active);


}
