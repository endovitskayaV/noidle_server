package noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.model.DataEntity;
import ru.vsu.noidle_server.model.repository.DataRepository;
import ru.vsu.noidle_server.service.DataService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataServiceImpl implements DataService {
    private final DataRepository dataRepository;


    public DataEntity getById(Integer id) {
        return dataRepository.getOne(id);
    }

    public List<DataEntity> getAll() {
        return dataRepository.findAll();
    }

    public DataEntity saveDataEntity(DataEntity dataEntity) {
        log.info("Saved {}", dataEntity);
        return dataRepository.save(dataEntity);
    }
}
