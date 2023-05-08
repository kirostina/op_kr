package com.coursework.petition.services;

import com.coursework.petition.models.Petition;

import java.util.List;
import java.util.Optional;

public interface PetitionService {
    List<Petition> getAllPetitions();

    void savePetition(Petition petition);

    void deletePetition(long id);

    Optional<Petition> findPetitionById(long id);
}
