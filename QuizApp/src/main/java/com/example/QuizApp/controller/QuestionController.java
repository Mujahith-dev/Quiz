package com.example.QuizApp.controller;

import com.example.QuizApp.entity.Question;
import com.example.QuizApp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getQuestions();
    }

    @GetMapping("/category/{value}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("value") String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("/addQuestions")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<String>deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }
    @PutMapping("/editQuestion/{id}")
    public ResponseEntity<String> editQuestion(@PathVariable Integer id, @RequestBody Question newQuestion){
        return questionService.editQuestion(id, newQuestion);
    }
}
