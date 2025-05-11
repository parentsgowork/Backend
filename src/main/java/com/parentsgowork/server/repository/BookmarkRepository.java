package com.parentsgowork.server.repository;

import com.parentsgowork.server.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("SELECT b FROM Bookmark b " +
            "WHERE b. user.id = :userId")
    List<Bookmark> findBookmarkList(@Param("userId") Long userId);

    Optional<Bookmark> findByIdAndUserId(@Param("bookmarkId") Long bookmarkId, @Param("userId") Long userId);
}
