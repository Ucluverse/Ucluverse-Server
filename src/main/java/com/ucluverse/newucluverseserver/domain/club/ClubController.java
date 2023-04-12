package com.ucluverse.newucluverseserver.domain.club;

import com.ucluverse.newucluverseserver.domain.club.dto.ClubEnrollRequest;
import com.ucluverse.newucluverseserver.domain.club.entity.Club;
import com.ucluverse.newucluverseserver.domain.club.entity.MemberClub;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;

    @PostMapping("/enroll")
    public ResponseEntity<MemberClub> clubEnroll (@RequestBody ClubEnrollRequest dto, Principal principal){
        String memberEmail = principal.getName();
        return ResponseEntity.ok(clubService.clubEnroll(dto, memberEmail));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Club>> getAllClubs(){
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @GetMapping()
    public ResponseEntity<Club> getOneClub(@RequestParam int club_id){
        System.out.println(club_id);
        return ResponseEntity.ok(clubService.getOneClub(club_id));
    }

}
