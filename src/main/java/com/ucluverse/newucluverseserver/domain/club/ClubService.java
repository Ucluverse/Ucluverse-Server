package com.ucluverse.newucluverseserver.domain.club;

import com.ucluverse.newucluverseserver.domain.club.dto.ClubEnrollRequest;
import com.ucluverse.newucluverseserver.domain.club.entity.Club;
import com.ucluverse.newucluverseserver.domain.club.entity.MemberClub;
import com.ucluverse.newucluverseserver.domain.club.repository.ClubRepository;
import com.ucluverse.newucluverseserver.domain.club.repository.MemberClubRepository;
import com.ucluverse.newucluverseserver.domain.member.Member;
import com.ucluverse.newucluverseserver.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubService {
    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final MemberClubRepository memberClubRepository;

    public MemberClub clubEnroll(ClubEnrollRequest dto, String memberEmail){
        Member member = memberRepository.findOneByEmail(memberEmail).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Club club = clubRepository.findOneById(dto.getClub_id()).orElseThrow(() -> new IllegalArgumentException("동아리를 찾을 수 없습니다."));
        MemberClub memberClub = memberClubRepository.findOneByMemberAndClub(member, club).orElseGet(()-> MemberClub.builder().member(member).club(club).role(MemberClubRole.CLUB_GUEST).build());
        memberClub.updateStatus(true);
        return memberClubRepository.save(memberClub);
    }
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club getOneClub(int club_id) {
        return clubRepository.findOneById(club_id).orElseThrow(() -> new IllegalArgumentException("동아리를 찾을 수 없습니다."));
    }
}
