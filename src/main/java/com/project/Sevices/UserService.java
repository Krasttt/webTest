package com.project.Sevices;

import com.project.domain.Result;
import com.project.domain.UserAccount;

import java.util.List;

/**
 *
 */
public interface UserService {
    /**
     * Метод изменяет firsName и(или) surName у пользователя.
     * @param firstName
     * @param surName
     * @param user
     */
    void editUserInfo(String firstName, String surName, UserAccount user);

    /**
     * Метод возвращает из БД результы тестов для данного пользователя.
     *
     * @param user
     * @return List<Result>
     */
    List<Result> getResults(UserAccount user);

    /**
     * Метод возврашает из БД список всех пользователей.
     *
     * @return List<UserAccount>
     */
    List<UserAccount> getAllUsers();

    /**
     * Метод возвращает из БД пользователя по id.
     * @param id
     * @return UserAccount
     */
    UserAccount getUser(Integer id);

    /**
     * Метод устанавливает пользователю по id роль ADMIN
     *
     * @param id
     * @return boolean
     */
    boolean setAdmin(Integer id);

    /**
     * Метод изменяет текущий пароль пользователя.
     *
     * @param curPassword
     * @param newPassword
     * @param repPassword
     * @param user
     * @return boolean
     */
    boolean editUserPassword(String curPassword, String newPassword, String repPassword, UserAccount user);

}
