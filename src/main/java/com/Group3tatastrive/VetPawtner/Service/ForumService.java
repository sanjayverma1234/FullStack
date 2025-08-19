package com.Group3tatastrive.VetPawtner.Service;

import com.Group3tatastrive.VetPawtner.Entity.Forum;
import com.Group3tatastrive.VetPawtner.Repository.ForumRepository;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ForumService {
    @Autowired
    private ForumRepository forumRepository;

    public List<Forum> getalldetails(){
        return forumRepository.findAll();
    }

    public Forum insertalldetails( Forum forum){
        return forumRepository.save(forum);
    }
    public Forum updatealldetail(Integer forumid,Forum forum){
        Forum forum1 = forumRepository.findById(forumid).orElse(null);
        forum1.setForumId(forum.getForumId());
        forum1.setUser(forum.getUser());
        forum1.setTitle(forum.getTitle());
        forum1.setContent(forum.getContent());
        forum1.setParent(forum.getParent());
        return forumRepository.save(forum1);
    }
    public Forum deletealldetails(Integer forumid){
        Forum forum =  forumRepository.findById(forumid).orElse(null);
        if(forum!=null){
            forumRepository.delete(forum);
        }
        return forum;
    }
}
