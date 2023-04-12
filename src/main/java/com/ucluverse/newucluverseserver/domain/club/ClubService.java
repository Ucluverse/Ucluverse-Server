package com.ucluverse.newucluverseserver.domain.club;

import com.ucluverse.newucluverseserver.domain.club.dto.EnrollClubRequest;
import com.ucluverse.newucluverseserver.domain.club.dto.StarClubRequest;
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

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club getOneClub(Integer club_id) {
        return clubRepository.findOneById(club_id);
    }

    public List<Club> getMyStarredClubs(String memberEmail) {
        return clubRepository.findAllByMemberClubsStatusMemberEmail(memberEmail);
    }

    public MemberClub clubEnroll(EnrollClubRequest dto, String memberEmail){
        Member member = memberRepository.findOneByEmail(memberEmail).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Club club = clubRepository.findOneById(dto.getClub_id());
        MemberClub memberClub = memberClubRepository.findOneByMemberAndClub(member, club).orElseGet(()-> MemberClub.builder().member(member).club(club).role(MemberClubRole.CLUB_GUEST).build());
        memberClub.updateStatus(!memberClub.isStatus());
        return memberClubRepository.save(memberClub);
    }

    public MemberClub starClub(StarClubRequest dto, String memberEmail) {
        Member member = memberRepository.findOneByEmail(memberEmail).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Club club = clubRepository.findOneById(dto.getClub_id());
        MemberClub memberClub = memberClubRepository.findOneByMemberAndClub(member, club).orElseGet(()-> MemberClub.builder().member(member).club(club).role(MemberClubRole.CLUB_GUEST).build());
        memberClub.updateStar(!memberClub.isStar());
        return memberClubRepository.save(memberClub);
    }
}
