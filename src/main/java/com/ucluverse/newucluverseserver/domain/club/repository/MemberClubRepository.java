package com.ucluverse.newucluverseserver.domain.club.repository;

import com.ucluverse.newucluverseserver.domain.club.entity.Club;
import com.ucluverse.newucluverseserver.domain.club.entity.MemberClub;
import com.ucluverse.newucluverseserver.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberClubRepository extends JpaRepository<MemberClub, Long> {
    Optional<MemberClub> findOneByMemberAndClub(Member member, Club club);
}
