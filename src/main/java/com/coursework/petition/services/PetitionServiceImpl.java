package com.coursework.petition.services;

import com.coursework.petition.models.Petition;
import com.coursework.petition.repo.PetitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetitionServiceImpl implements PetitionService {
    private final PetitionRepository petitionRepository;

    private final VoteService voteService;

    public PetitionServiceImpl(PetitionRepository petitionRepository, VoteService voteService) {
        this.petitionRepository = petitionRepository;
        this.voteService = voteService;
    }
    @Override
    public List<Petition> getAllPetitions() {
        return (List<Petition>) petitionRepository.findAll();
    }

    @Override
    public void savePetition(Petition petition) {
        petitionRepository.save(petition);
    }

    @Override
    public void deletePetition(long id) {
        Petition petition = findPetitionById(id).orElseThrow();
        voteService.deleteVotesByPetition(petition);
        petitionRepository.delete(petition);
    }

    @Override
    public Optional<Petition> findPetitionById(long id) {
        return petitionRepository.findById(id);
    }
}
