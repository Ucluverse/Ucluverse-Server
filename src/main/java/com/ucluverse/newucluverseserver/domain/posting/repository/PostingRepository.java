package com.ucluverse.newucluverseserver.domain.posting.repository;

import com.ucluverse.newucluverseserver.domain.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingRepository extends JpaRepository <Posting, Long> {
}
