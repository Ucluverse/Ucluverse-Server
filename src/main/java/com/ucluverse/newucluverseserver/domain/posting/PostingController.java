package com.ucluverse.newucluverseserver.domain.posting;

import com.ucluverse.newucluverseserver.domain.member.Member;
import com.ucluverse.newucluverseserver.domain.posting.dto.CreatePostingRequest;
import com.ucluverse.newucluverseserver.domain.posting.dto.UpdatePostingRequest;
import com.ucluverse.newucluverseserver.domain.posting.entity.Posting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posting")
public class PostingController {
    private final PostingService postingService;

    @GetMapping("/all")
    public ResponseEntity<List<Posting>> getAllPostings(){
        return ResponseEntity.ok(postingService.getAllPostings());
    }

    @GetMapping()
    public ResponseEntity<Posting> getOnePosting(@AuthenticationPrincipal Member member, @RequestParam long posting_id){
        return ResponseEntity.ok(postingService.getOnePostingById(member, posting_id));
    }

    @PostMapping()
    public ResponseEntity<Posting> createPosting(@AuthenticationPrincipal Member member, @RequestBody CreatePostingRequest dto){
        return ResponseEntity.ok(postingService.createPosting(member, dto));
    }

    @PatchMapping()
    public ResponseEntity<Posting> updatePosting(@AuthenticationPrincipal Member member, @RequestBody UpdatePostingRequest dto){
        return ResponseEntity.ok(postingService.updatePosting(member, dto));
    }

    @DeleteMapping()
    public ResponseEntity<String> deletePosting(@AuthenticationPrincipal Member member, @RequestParam long posting_id){
        postingService.deletePosting(member, posting_id);
        return ResponseEntity.ok("성공적으로 삭제됐습니다");
    }

}
