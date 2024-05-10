package com.smartflow.smartflow.control.wikitextcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartflow.smartflow.dto.wikitextdto.WikiTextRequest;
import com.smartflow.smartflow.model.WikiText;
import com.smartflow.smartflow.service.wikitextservice.WikiTextService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/wiki")
public class WikiTextController {

    @Autowired
    private WikiTextService wikiTextService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<String> saveOrUpdateWikiText(@RequestBody WikiTextRequest request) {
        if (request.getText() == null || request.getText().isEmpty()) {
            System.out.println("!!!!!!!!!!! TEXTO NULL OU VAZIO !!!!!!!!!!!!");
            System.out.println(request.getText());
        }
        wikiTextService.saveOrUpdateWikiText(request.getTeamId(), request.getText());
        return ResponseEntity.status(HttpStatus.CREATED).body("Wiki text saved or updated successfully.");
    }

    @GetMapping("/getByTeamId/{teamId}")
    public ResponseEntity<WikiText> getWikiTextByTeamId(@PathVariable Integer teamId) {
        WikiText wikiText = wikiTextService.getWikiTextByTeamId(teamId);
        return wikiText != null ? ResponseEntity.ok(wikiText) : ResponseEntity.notFound().build();
    }

}
