package com.project.controllers;

import com.project.domain.Answer;
import com.project.domain.Type;
import com.project.domain.UserAnswer;
import com.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EditTestController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ResultRepository resultRepository;

  
}
