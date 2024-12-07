package orderme.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import orderme.service.TableService;
import orderme.service.dto.TablesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TablesController {

    private final TableService tableService;

    @GetMapping()
    public ResponseEntity<List<TablesDto>> getTablesByUser() {
        try {
            List<TablesDto> result = tableService.getTablesByUser();
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<TablesDto> createTable(@RequestBody TablesDto tablesDto) {
        try {
            TablesDto createdTable = tableService.createTable(tablesDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTable);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{tableId}")
    public ResponseEntity<TablesDto> updateTable(@PathVariable Integer tableId, @RequestBody TablesDto tablesDto) {
        try {
            TablesDto updatedTable = tableService.updateTable(tableId, tablesDto);
            return ResponseEntity.ok(updatedTable);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{tableId}")
    public ResponseEntity<Void> deleteTable(@PathVariable Integer tableId) {
        try {
            tableService.deleteTable(tableId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
