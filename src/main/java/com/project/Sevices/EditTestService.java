package com.project.Sevices;

import com.project.domain.Answer;
import com.project.domain.Question;

import java.util.List;

/**
 * Сервис предназначен для изменения,удаления вопросов теста.
 */
public interface EditTestService {
    /**
     * Метод изменяет возможные варианты ответа на вопрос.
     *
     * @param id
     * @param answers
     * @return List<Answer>
     */
    List<Answer> editQuestionAnswers(Integer id, List<Answer> answers);

    /**
     * Метод возвращает все ответы на вопросы, которые предали в параметре.
     *
     * @param questions
     * @return List<Answer>
     */
    List<Answer> getAnswers(List<Question> questions);

    /**
     * Метод возвращает все вопросы, принадлежащие конкретному тесту
     *
     * @param id
     * @return List<Question>
     */
    List<Question> getQuestions(Integer id);

    /**
     * Метод изменяет текст вопроса и сохраняет изменение в БД.
     *
     * @param id
     * @param questionText
     */
    void editQuestionText(Integer id, String questionText);

    /**
     * Метод удаляет из БД вопрос.
     *
     * @param testId
     * @param questionId
     */
    void deleteQuestion(Integer testId,Integer questionId);
}
