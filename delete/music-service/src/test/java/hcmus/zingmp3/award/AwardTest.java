package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.model.AwardResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AwardTest {
//    @Mock
//    private AwardService awardService;

    @Mock
    private AwardRepository awardRepository;

    @Mock
    private AwardMapper awardMapper;

    @InjectMocks
    private AwardServiceImpl awardService;

    @Test
    public void whenGetAll_shouldReturnList() {
//        Award award1 = new Award();
//        Award award2 = new Award();
//        List<Award> awardList = Arrays.asList(award1, award2);
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Award> awardPage = new PageImpl<>(awardList, pageable, awardList.size());
//
//        when(awardRepository.findAll(pageable)).thenReturn(awardPage);
//
//        when(awardMapper.toDTO(awardPage.getContent())).thenReturn(Arrays.asList(new AwardResponse(), new AwardResponse()));
//
//        assertEquals(2, awardService.getAllAwards(pageable).size());

    }

}
