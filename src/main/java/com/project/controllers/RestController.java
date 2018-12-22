package com.project.controllers;

import com.project.domain.Answer;
import com.project.domain.Type;
import com.project.domain.UserAccount;
import com.project.domain.UserAnswer;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import com.project.repositories.ResultRepository;
import com.project.repositories.UserAnswerRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final ResultRepository resultRepository;

    public RestController(QuestionRepository questionRepository,
                          AnswerRepository answerRepository,
                          UserAnswerRepository userAnswerRepository,
                          ResultRepository resultRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.resultRepository = resultRepository;
    }

    @PostMapping("/editTest/{id}")
    public List<Answer> editQuestion(@PathVariable Integer id, @RequestBody List<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.getText().equals("")) {
                answer.setText(answerRepository.findById(answer.getId()).getText());
            }
            answer.setQuestion(questionRepository.findById(id));
        }
        answerRepository.saveAll(answers);
        return answers;
    }

    @PostMapping("/test/addAnswer/{id}/{result_id}")
    public void confirmAnswer(@PathVariable Integer id, @PathVariable("result_id") Integer resultId,
                              @RequestBody List<Answer> answers,@AuthenticationPrincipal UserAccount activeUser
    ) {
        Type typeQuestion = questionRepository.findById(id).getType();
        for (Answer answer : answers) {
            if (typeQuestion != Type.WORD) {
                if (answerRepository.findById(answer.getId()).isCorrect()) {
                    userAnswerRepository.save(new UserAnswer(answer.getText(), true,
                            questionRepository.findById(id), resultRepository.findById(resultId),activeUser));
                } else
                    userAnswerRepository.save(new UserAnswer(answer.getText(), false,
                            questionRepository.findById(id), resultRepository.findById(resultId),activeUser));

            } else userAnswerRepository.save(new UserAnswer(answer.getText(),
                    answer.getText().equals(answerRepository.findById(answer.getId()).getText()),
                    questionRepository.findById(id), resultRepository.findById(resultId),activeUser));
        }
        return;
    }

}
