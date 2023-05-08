package com.coursework.petition.controllers;


import com.coursework.petition.models.*;
import com.coursework.petition.services.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/petition")
public class PetitionController {
    private final PetitionService petitionService;
    private final VoteService voteService;
    private final UserService userService;

    public PetitionController(PetitionService petitionService, VoteService voteService, UserService userService) {
        this.petitionService = petitionService;
        this.voteService = voteService;
        this.userService = userService;
    }
    @GetMapping()
    public String petitionMain(Model model) {
        List<Petition> petitions = petitionService.getAllPetitions();
        model.addAttribute("petitions", petitions);
        return "main";
    }

    @GetMapping("/new/add")
    public String petitionAdd(@ModelAttribute("post") Petition petitions) {
        return "petition-add";
    }
    @PostMapping("/new/add")
    public String PetitionAdd(@ModelAttribute("post") @Valid Petition petition,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "petition-add";
        petitionService.savePetition(petition);
        return "redirect:/petition";
    }
    @GetMapping("/{id}")
    public String petitionInfo(@PathVariable(value = "id") long id, Model model, Principal principal) {
        Optional<Petition> petition = petitionService.findPetitionById(id);
        if (petition.isEmpty()) {
            return "redirect:/petition";
        }

        model.addAttribute("petition", petition.get());

        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("currentUser", user);
        }

        return "petition-info";
    }
    @PostMapping("/{id}/remove")
    public String petitionRemove(@PathVariable(value = "id") long id, Model model) {
        voteService.deleteVotesByPetitionId(id);
        petitionService.deletePetition(id);
        return "redirect:/petition";
    }
    @PostMapping("/{id}/vote")
    public String voteForPetition(@PathVariable(value = "id") long id, @RequestParam("isUpvote") boolean isUpvote, Principal principal) {
        Optional<Petition> petition = petitionService.findPetitionById(id);
        if (petition.isPresent()) {
            Vote vote = new Vote();
            vote.setPetition(petition.get());
            vote.setIsUpvote(isUpvote);
            voteService.saveVote(vote);

            if (principal != null) {
                String username = principal.getName();
                User user = userService.findByUsername(username);
                user.getPetitionsVoted().add(petition.get());
                userService.save(user);
            }

            petition.get().setVoted(true);
            petitionService.savePetition(petition.get());
        }
        return "redirect:/petition/{id}";
    }
    @GetMapping("/{id}/link")
    public String petitionLink(@PathVariable(value = "id") long id, Model model) {
        String link = "http://localhost:8052/petition/" + id;
        model.addAttribute("link", link);
        return "petition-link";
    }
}