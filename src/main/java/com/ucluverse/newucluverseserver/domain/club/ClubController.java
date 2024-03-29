package com.ucluverse.newucluverseserver.domain.club;

import com.ucluverse.newucluverseserver.domain.club.dto.EnrollClubRequest;
import com.ucluverse.newucluverseserver.domain.club.dto.StarClubRequest;
import com.ucluverse.newucluverseserver.domain.club.entity.Club;
import com.ucluverse.newucluverseserver.domain.club.entity.MemberClub;
import com.ucluverse.newucluverseserver.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;

    @GetMapping("/all")
    public ResponseEntity<List<Club>> getAllClubs(){
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @GetMapping()
    public ResponseEntity<Club> getOneClub(@RequestParam int club_id){
        return ResponseEntity.ok(clubService.getOneClub(club_id));
    }

    @GetMapping("/starred")
    public ResponseEntity<List<Club>> getMyStarredClubs(Principal principal){
        String memberEmail = principal.getName();
        return ResponseEntity.ok(clubService.getMyStarredClubs(memberEmail));
    }

    @PostMapping("/enroll")
    public ResponseEntity<MemberClub> clubEnroll (@AuthenticationPrincipal Member member, @RequestBody EnrollClubRequest dto){
        return ResponseEntity.ok(clubService.clubEnroll(member, dto));
    }

    @PostMapping("/star")
    public ResponseEntity<MemberClub> starClub(@AuthenticationPrincipal Member member, @RequestBody StarClubRequest dto){
        return ResponseEntity.ok(clubService.starClub(member, dto));
    }

}
