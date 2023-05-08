package com.coursework.petition.services;

import com.coursework.petition.models.Petition;
import com.coursework.petition.models.Vote;
import com.coursework.petition.repo.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public Optional<Vote> findVoteById(long id) {
        return voteRepository.findById(id);
    }

    @Override
    public void deleteVotesByPetition(Petition petition) {
        List<Vote> votes = voteRepository.findByPetition(petition);
        for (Vote vote : votes) {
            voteRepository.delete(vote);
        }
    }

    @Override
    public void deleteVotesByPetitionId(long petitionId) {
        voteRepository.deleteByPetitionId(petitionId);
    }
}