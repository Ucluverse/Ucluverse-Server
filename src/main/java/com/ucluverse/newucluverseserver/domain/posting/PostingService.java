package com.ucluverse.newucluverseserver.domain.posting;

import com.ucluverse.newucluverseserver.common.CustomResponseStatusException;
import com.ucluverse.newucluverseserver.common.ErrorCode;
import com.ucluverse.newucluverseserver.domain.member.Member;
import com.ucluverse.newucluverseserver.domain.posting.dto.CreatePostingRequest;
import com.ucluverse.newucluverseserver.domain.posting.dto.UpdatePostingRequest;
import com.ucluverse.newucluverseserver.domain.posting.entity.ClubPosting;
import com.ucluverse.newucluverseserver.domain.posting.entity.Posting;
import com.ucluverse.newucluverseserver.domain.posting.repository.ClubPostingRepository;
import com.ucluverse.newucluverseserver.domain.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostingService {
    private final ClubPostingRepository clubPostingRepository;
    private final PostingRepository postingRepository;

    public Posting createPosting(Member member, CreatePostingRequest dto) {
        ClubPosting clubPosting = clubPostingRepository.findOneById(dto.getClubPosting_id())
                .orElseThrow(()-> new CustomResponseStatusException(ErrorCode.CLUB_POSTING_NOT_FOUND));
        Posting posting = Posting.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .isPublic(dto.isPublic())
                .allowComments(dto.isAllowComments())
                .clubPosting(clubPosting)
                .member(member)
                .build();
        return postingRepository.save(posting);
    }

    public List<Posting> getAllPostings() {
        return postingRepository.findAll();
    }

    public Posting getOnePostingById(Member member, long posting_id) {
        return postingRepository.findById(posting_id)
                .orElseThrow(() -> new CustomResponseStatusException(ErrorCode.POSTING_NOT_FOUND));
    }

    public Posting updatePosting(Member member, UpdatePostingRequest dto) {
        Posting posting = postingRepository.findById(dto.getId())
                .orElseThrow(() -> new CustomResponseStatusException(ErrorCode.POSTING_NOT_FOUND));
        if (!posting.getMember().getId().equals(member.getId())){
            throw new CustomResponseStatusException(ErrorCode.INVALID_MEMBER_UPDATE_POSTING);
        }
        posting.update(dto);
        return postingRepository.save(posting);
    }

    public void deletePosting(Member member, long posting_id) {
        Posting posting = postingRepository.findById(posting_id)
                .orElseThrow(() -> new CustomResponseStatusException(ErrorCode.POSTING_NOT_FOUND));
        if (!posting.getMember().getId().equals(member.getId())){
            throw new CustomResponseStatusException(ErrorCode.INVALID_MEMBER_UPDATE_POSTING);
        }
        postingRepository.delete(posting);
    }
}
