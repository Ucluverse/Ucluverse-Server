package com.ucluverse.newucluverseserver.domain.club;

import com.ucluverse.newucluverseserver.domain.club.dto.ClubEnrollRequest;
import com.ucluverse.newucluverseserver.domain.club.entity.MemberClub;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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

}
