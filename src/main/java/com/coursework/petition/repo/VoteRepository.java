package com.coursework.petition.repo;

import com.coursework.petition.models.Petition;
import com.coursework.petition.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    void deleteByPetitionId(long petitionId);

    List<Vote> findByPetition(Petition petition);
}
