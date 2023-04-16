package com.ucluverse.newucluverseserver.domain.club.repository;

import com.ucluverse.newucluverseserver.domain.club.entity.Club;
import com.ucluverse.newucluverseserver.domain.club.entity.MemberClub;
import com.ucluverse.newucluverseserver.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberClubRepository extends JpaRepository<MemberClub, Long> {
    Optional<MemberClub> findOneByMemberAndClub(Member member, Club club);
}
