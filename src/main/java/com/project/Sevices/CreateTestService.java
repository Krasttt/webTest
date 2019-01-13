package com.project.Sevices;

import com.project.domain.UserAccount;
/**
 * Сервис предназначен для создания теста и добавления вопросов в него.
 */
public interface CreateTestService {
    /**
     * Метод создает тест по заданным параметрам и сохраняет его в БД.
     *
     * @param name
     * @param description
     * @param duration
     * @param user
     */
    void addTest(String name, String description, Integer duration, UserAccount user);

    /**
     * Метод создает вопрос (типа "MULTI") и ответы по заданным параметрам и сохраняет эти данные в БД.
     *
     * @param textQuestion
     * @param id
     * @param check1
     * @param check2
     * @param check3
     * @param check4
     * @param answer1
     * @param answer2
     * @param answer3
     * @param answer4
     */
    void addMultiQuestion(String textQuestion, String id, String check1, String check2, String check3, String check4,
                          String answer1, String answer2, String answer3, String answer4);

    /**
     * Метод создает вопрос (типа "SINGLE") и ответы по заданным параметрам и сохраняет эти данные в БД.
     *
     * @param textQuestion
     * @param corAnswer
     * @param answer2
     * @param answer3
     * @param answer4
     * @param id
     */
    void addSingleQuestion(String textQuestion, String corAnswer, String answer2,
                           String answer3, String answer4, String id);

    /**
     * Метод создает вопрос (типа "WORD") и ответ по заданным параметрам и сохраняет эти данные в БД.
     *
     * @param textQuestion
     * @param answer
     * @param id
     */
    void addWordQuestion(String textQuestion, String answer, String id);
}
