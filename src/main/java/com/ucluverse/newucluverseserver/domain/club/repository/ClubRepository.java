package com.ucluverse.newucluverseserver.domain.club.repository;

import com.ucluverse.newucluverseserver.domain.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findOneById(int club_id);
}
