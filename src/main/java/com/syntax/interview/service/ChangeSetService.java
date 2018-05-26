package com.syntax.interview.service;

import com.syntax.interview.service.dto.report.Diff;

public interface ChangeSetService {

    Diff getDiffReport(Long previousId, Long currentId);
}
