package com.project.Sevices;

import com.project.domain.UserAccount;

/**
 *Сервис предназначин для регистрации новых пользователей.
 */
public interface RegistrationService {
    /**
     * Метод проверяет на корректность нового пользователя и добавляет его в БД,если все данные удовлетворяют.
     *
     * @param userAccount
     * @return boolean
     */
    boolean addUser(UserAccount userAccount);
}
