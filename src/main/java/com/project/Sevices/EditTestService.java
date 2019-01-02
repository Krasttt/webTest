package com.project.Sevices;

import com.project.domain.Answer;
import com.project.domain.Question;
import com.project.repositories.AnswerRepository;
import com.project.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditTestService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public List<Answer> editQuestionAnswers(Integer id, List<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.getText().equals("")) {
                answer.setText(answerRepository.findById(answer.getId()).getText());
            }
            answer.setQuestion(questionRepository.findById(id));
        }
        answerRepository.saveAll(answers);
        return answers;
    }

    public List<Answer> getAnswers(List<Question> questions) {
        List<Answer> answers = new ArrayList<>();

        for (Question q : questions) {
            answers.addAll(answerRepository.findByQuestionId(q.getId()));
        }
        return answers;
    }

    public List<Question> getQuestions(Integer id) {
        return questionRepository.findByTestId(id);
    }

    public void editQuestionText(Integer id, String questionText) {
        Question question = questionRepository.findById(id);
        question.setText(questionText);
        questionRepository.save(question);
    }
}
