package com.parentsgowork.server.service.jobInfoService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.JobInfoHandler;
import com.parentsgowork.server.converter.JobInfoConverter;
import com.parentsgowork.server.domain.JobInfo;
import com.parentsgowork.server.repository.JobInfoRepository;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobInfoQueryServiceImpl implements JobInfoQueryService {

    private final JobInfoRepository jobInfoRepository;

    @Override
    public List<JobInfoResponseDTO.JobInfoListDTO> getJobInfoList(Long userId) {

        List<JobInfo> jobInfos = jobInfoRepository.findJobInfoList(userId);

        if(jobInfos == null || jobInfos.isEmpty()) {
            throw new JobInfoHandler(ErrorStatus.JOB_INFO_NOT_FOUND);
        }

        return JobInfoConverter.getJobInfoListDTO(jobInfos);
    }

    @Override
    public JobInfoResponseDTO.JobInfoDetailDTO getJobInfoDetails(Long userId, Long jobInfoId) {

        JobInfo jobInfo = jobInfoRepository.findByIdAndUserId(jobInfoId, userId)
                .orElseThrow(() -> new JobInfoHandler(ErrorStatus.JOB_INFO_NOT_FOUND));

        return JobInfoConverter.getJobInfoDetailDTO(jobInfo);
    }
}
