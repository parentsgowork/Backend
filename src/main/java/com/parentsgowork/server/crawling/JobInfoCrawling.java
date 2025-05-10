package com.parentsgowork.server.crawling;

import com.parentsgowork.server.util.WebDriverUtil;
import com.parentsgowork.server.web.dto.JobCrawlingDTO.JobCrawlingDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class JobPageCrawling {

    public List<JobCrawlingDTO.JobInfoDTO> crawlJobs(int page) {
        WebDriver driver = WebDriverUtil.getChromeDriver();
        List<JobCrawlingDTO.JobInfoDTO> jobs = new ArrayList<>();

        try {
            String url = "https://www.work24.go.kr/wk/a/b/1200/retriveDtlEmpSrchList.do?" +
                    "basicSetupYn=&careerTo=&keywordJobCd=&occupation=&seqNo=&cloDateEndtParam=&payGbn=&templateInfo=&rot2WorkYn=&shsyWorkSecd=&resultCnt=10" +
                    "&keywordJobCont=" + (page > 1 ? "N" : "") +
                    "&cert=&moreButtonYn=Y&minPay=&codeDepth2Info=11000&currentPageNo=1&eventNo=&mode=&major=&resrDutyExcYn=&eodwYn=&sortField=DATE&staArea=&sortOrderBy=DESC&keyword=&termSearchGbn=&carrEssYns=&benefitSrchAndOr=O&disableEmpHopeGbn=&actServExcYn=" +
                    "&keywordStaAreaNm=" + (page > 1 ? "N" : "") +
                    "&maxPay=&emailApplyYn=&codeDepth1Info=11000&keywordEtcYn=&regDateStdtParam=&publDutyExcYn=&keywordJobCdSeqNo=&viewType=&exJobsCd=&templateDepthNmInfo=&region=&employGbn=&empTpGbcd=1&computerPreferential=&infaYn=&cloDateStdtParam=&siteClcd=all&searchMode=Y" +
                    "&birthFromYY=&indArea=&careerTypes=&subEmpHopeYn=&tlmgYn=&academicGbn=&templateDepthNoInfo=&foriegn=&entryRoute=&mealOfferClcd=&basicSetupYnChk=&station=&holidayGbn=&srcKeyword=&academicGbnoEdu=noEdu&enterPriseGbn=&cloTermSearchGbn=&birthToYY=" +
                    "&keywordWantedTitle=" + (page > 1 ? "N" : "") +
                    "&stationNm=&benefitGbn=&keywordFlag=&notSrcKeyword=&essCertChk=&depth2SelCode=" +
                    "&keywordBusiNm=" + (page > 1 ? "N" : "") +
                    "&preferentialGbn=&rot3WorkYn=&regDateEndtParam=&pfMatterPreferential=B" +
                    "&pageIndex=" + page +
                    "&termContractMmcnt=&careerFrom=&laborHrShortYn=#scrollLoc";


            driver.get(url);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            List<WebElement> jobCards = driver.findElements(By.cssSelector("tr[id^='list']"));

            for (WebElement card : jobCards) {
                String company = card.findElement(By.cssSelector("a.cp_name")).getText();
                String title = card.findElement(By.cssSelector("a.t3_sb")).getText();
                String pay = card.findElement(By.cssSelector("li.dollar span.item")).getText();
                String time = card.findElement(By.cssSelector("li.time span.item.sm:nth-of-type(1)")).getText();
                String location = card.findElement(By.cssSelector("li.site p")).getText();

                String deadline = "", registrationDate = "";

                List<WebElement> dateElements = card.findElements(By.cssSelector("p[class='s1_r']"));
                if (dateElements.size() >= 2) {
                    deadline = dateElements.get(0).getText();
                    registrationDate = dateElements.get(1).getText();
                }

                System.out.println(company + " / " + title + " / " + pay + " / " + time + " / " + location + " / " + deadline + " / " + registrationDate);

                jobs.add(
                        JobCrawlingDTO.JobInfoDTO.builder()
                                .companyName(company)
                                .jobTitle(title)
                                .pay(pay)
                                .workTime(time)
                                .location(location)
                                .deadline(deadline)
                                .registrationDate(registrationDate)
                                .build()
                );
            }
        } catch (NoSuchElementException e) {
            throw new BusinessException(ErrorStatus.CRAWLING_ELEMENT_NOT_FOUND);
        } catch (TimeoutException e) {
            throw new BusinessException(ErrorStatus.CRAWLING_TIMEOUT);
        } catch (SessionNotCreatedException e) {
            throw new BusinessException(ErrorStatus.CRAWLING_SESSION_FAIL);
        } catch (Exception e) {
            throw new BusinessException(ErrorStatus.CRAWLING_UNKNOWN_ERROR);
        } finally {
            WebDriverUtil.quit(driver);
        }

        return jobs;
    }
}
