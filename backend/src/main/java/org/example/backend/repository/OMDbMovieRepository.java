package org.example.backend.repository;

import org.example.backend.model.OMDbMovie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OMDbMovieRepository extends MongoRepository<OMDbMovie, String> {
    Optional<OMDbMovie> findByTitle(String Title);
}
