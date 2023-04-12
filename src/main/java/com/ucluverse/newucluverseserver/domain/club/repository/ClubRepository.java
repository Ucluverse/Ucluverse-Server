package com.ucluverse.newucluverseserver.domain.club.repository;

import com.ucluverse.newucluverseserver.domain.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Club findOneById(int club_id);
    @Query("SELECT c FROM Club c JOIN c.memberClubs mc JOIN  mc.member m WHERE m.email = ?1 AND mc.star = true")
    List<Club> findAllByMemberClubsStatusMemberEmail(String MemberEmail);
}
