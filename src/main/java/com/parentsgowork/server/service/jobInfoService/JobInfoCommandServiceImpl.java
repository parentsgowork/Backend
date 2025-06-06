package com.parentsgowork.server.service.jobInfoService;

import com.parentsgowork.server.apiPayload.code.status.ErrorStatus;
import com.parentsgowork.server.apiPayload.exception.JobInfoHandler;
import com.parentsgowork.server.apiPayload.exception.UserHandler;
import com.parentsgowork.server.converter.JobInfoConverter;
import com.parentsgowork.server.domain.JobInfo;
import com.parentsgowork.server.domain.User;
import com.parentsgowork.server.repository.JobInfoRepository;
import com.parentsgowork.server.repository.UserRepository;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoRequestDTO;
import com.parentsgowork.server.web.dto.JobInfoDTO.JobInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobInfoCommandServiceImpl implements JobInfoCommandService {

    private final UserRepository userRepository;
    private final JobInfoRepository jobInfoRepository;

    @Override
    public List<JobInfoResponseDTO.AddJobResultDTO> addJobInfo(Long userId, List<JobInfoRequestDTO.SaveJobInfoDTO> saveJobInfoDTOList) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        for (JobInfoRequestDTO.SaveJobInfoDTO dto : saveJobInfoDTOList) {
            boolean exists = jobInfoRepository.existsByTitleAndContent(dto.getTitle(), dto.getContent());
            if (exists) {
                throw new JobInfoHandler(ErrorStatus.JOB_INFO_ALREADY_EXISTS);
            }
        }

        List<JobInfo> jobInfos = saveJobInfoDTOList.stream()
                .map(dto -> JobInfo.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .user(user)
                        .build())
                .collect(Collectors.toList());

        jobInfoRepository.saveAll(jobInfos);

        return jobInfos.stream()
                .map(JobInfoConverter::toAddJobResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobInfoResponseDTO.DeleteJobInfoDTO delete(Long jobInfoId, Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        JobInfo jobInfo = jobInfoRepository.findByIdAndUserId(jobInfoId, userId)
                .orElseThrow(() -> new JobInfoHandler(ErrorStatus.JOB_INFO_NOT_FOUND));

        jobInfoRepository.deleteById(jobInfoId);

        return JobInfoConverter.DeleteJobInfoDTO(jobInfo);
    }

}
