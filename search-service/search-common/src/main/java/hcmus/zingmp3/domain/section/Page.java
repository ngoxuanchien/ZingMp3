package hcmus.zingmp3.domain.section;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Page {
    private List<?> items;
    private boolean hasMore;
    private int total;
}
