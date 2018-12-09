package com.project.controllers;

import com.project.domain.Answer;
import com.project.domain.Question;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    final QuestionRepository questionRepository;
    final AnswerRepository answerRepository;

    public RestController(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Modifying
    @PostMapping("/editTest")
    public List<Answer> editQuestion(@RequestParam (required = false) String id, @RequestParam Map<String,String> json) {
        List<Answer> answers =new ArrayList<>();
        for (int i =0; i<json.size()/4;i++){
            Answer ans = answerRepository.findById(Integer.parseInt(json.get("answers["+i+"][id]")));

            String text=json.get("answers["+i+"][text]");
            Question q = questionRepository.findById(Integer.parseInt(json.get("answers["+i+"][q_id]")));
            boolean flag = json.get("answers["+i+"][correct]").equals("true")?true:false;

            if (!text.equals("")) ans.setText(text);
            ans.setCorrect(flag);
            ans.setQuestion(q);
            answers.add(ans);

            answerRepository.save(ans);
        }
        return answers;
    }
}
