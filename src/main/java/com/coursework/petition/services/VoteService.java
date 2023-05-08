package com.coursework.petition.services;

import com.coursework.petition.models.Petition;
import com.coursework.petition.models.Vote;

import java.util.Optional;

public interface VoteService {
    void saveVote(Vote vote);

    Optional<Vote> findVoteById(long id);

    void deleteVotesByPetition(Petition petition);

    void deleteVotesByPetitionId(long id);
}
