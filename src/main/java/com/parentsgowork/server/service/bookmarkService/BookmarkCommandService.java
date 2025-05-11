package com.parentsgowork.server.service.bookmarkService;

import com.parentsgowork.server.domain.Bookmark;

public interface BookmarkService {
    Bookmark bookmarkJob(Long jobId, int page);
}
