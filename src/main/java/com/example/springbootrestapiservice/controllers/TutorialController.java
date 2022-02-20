package com.example.springbootrestapiservice.controllers;

import com.example.springbootrestapiservice.exceptions.ResourceNotFoundException;
import com.example.springbootrestapiservice.models.Tutorial;
import com.example.springbootrestapiservice.repositories.TutorialRepository;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<>();

        if (title == null) {
            tutorialRepository.findAll().forEach(tutorials::add);
        } else {
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        }

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id) {
        Tutorial tutorial = tutorialRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found tutorial with id = " + id));

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial newTutorial = tutorialRepository.save(
                new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));

        return new ResponseEntity<>(newTutorial, HttpStatus.OK);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") Long id, @RequestBody Tutorial tutorial) {
        Tutorial tutorialToEdit = tutorialRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found tutorial with id = " + id));

        tutorialToEdit.setTitle(tutorial.getTitle());
        tutorialToEdit.setDescription(tutorial.getDescription());
        tutorialToEdit.setPublished(tutorial.isPublished());

        return new ResponseEntity<>(tutorialRepository.save(tutorialToEdit), HttpStatus.OK);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPulished() {
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

        if(tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }
}
