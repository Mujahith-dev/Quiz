package com.example.QuizApp.services;

import com.example.QuizApp.entity.Question;
import com.example.QuizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<List<Question>> getQuestions(){
        try{
        return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category){
        try {
            return new ResponseEntity<>(questionRepository.getQuestionByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question){
        try{
        questionRepository.save(question);
        return new ResponseEntity<>("Student Added", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not created", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(Integer id){
        try{
        questionRepository.deleteById(id);
        return new ResponseEntity<>("Question deleted", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> editQuestion(Integer id, Question newQuestion){
        try{
        Optional<Question> oldQuestion = questionRepository.findById(id);
        if(oldQuestion.isPresent()){
            Question existingQuestion = oldQuestion.get();
            existingQuestion.setQuestionTitle(newQuestion.getQuestionTitle());
            existingQuestion.setCategory(newQuestion.getCategory());
            existingQuestion.setDifficultyLevel(newQuestion.getDifficultyLevel());
            existingQuestion.setOption1(newQuestion.getOption1());
            existingQuestion.setOption2(newQuestion.getOption2());
            existingQuestion.setOption3(newQuestion.getOption3());
            existingQuestion.setOption4(newQuestion.getOption4());
            existingQuestion.setRightAnswer(newQuestion.getRightAnswer());
            questionRepository.save(existingQuestion);
            return new ResponseEntity<>("Question "+ id+" edited", HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Edited", HttpStatus.BAD_REQUEST);


    }
}
