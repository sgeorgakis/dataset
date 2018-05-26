package com.syntax.interview.service;

import com.syntax.interview.service.dto.changeset.ChangeSetData;
import com.syntax.interview.service.dto.report.Diff;

public interface DiffReportService {

    Diff calculateReport(ChangeSetData previous, ChangeSetData current);
}
