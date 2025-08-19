package com.Group3tatastrive.VetPawtner.Controller;

import com.Group3tatastrive.VetPawtner.Entity.Forum;
import com.Group3tatastrive.VetPawtner.Repository.ForumRepository;
import com.Group3tatastrive.VetPawtner.Service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demo/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;
    @GetMapping("/fetch")
    public ResponseEntity<List<Forum>> getallforumdetails(){
        return ResponseEntity.ok(forumService.getalldetails());
    }
    @PostMapping
    public ResponseEntity<Forum> insertintoforum(@RequestBody Forum forum){
        return ResponseEntity.ok(forumService.insertalldetails(forum));

    }
    @PutMapping("{id}")
    public ResponseEntity<Forum> updatealldetails(@PathVariable Integer forumid,@RequestBody Forum forum){
        return ResponseEntity.ok(forumService.updatealldetail(forumid,forum));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Forum> deletealldetails(@PathVariable Integer forumid){
        return ResponseEntity.ok(forumService.deletealldetails(forumid));
    }
}
