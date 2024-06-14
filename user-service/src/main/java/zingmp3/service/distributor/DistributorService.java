package zingmp3.service.distributor;

import zingmp3.web.dto.DistributorRequest;

public interface DistributorService {

    void register(DistributorRequest request);

    void delete(String email);
}
