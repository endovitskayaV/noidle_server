package noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.noidle_server.model.DataEntity;
import ru.vsu.noidle_server.service.DataService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class DataController {
    private final  DataService dataService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataEntity> getDataById(@PathVariable Integer id) {
        DataEntity data = dataService.getById(id);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DataEntity>> getAllData() {
        List<DataEntity> data = dataService.getAll();
        return ResponseEntity.ok(data);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataEntity> saveData(@RequestBody DataEntity dataEntity) {
        return ResponseEntity.ok(dataService.saveDataEntity(dataEntity));
    }
}
