package com.ucluverse.newucluverseserver.domain.posting.repository;

import com.ucluverse.newucluverseserver.domain.posting.entity.ClubPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubPostingRepository extends JpaRepository<ClubPosting, Long> {
    Optional<ClubPosting> findOneById(int clubPostingId);
}
