package giraldi.dev.com.ssejavaspring.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giraldi.dev.com.ssejavaspring.dtos.InsertTaskDto;
import giraldi.dev.com.ssejavaspring.dtos.ListTaskDto;
import giraldi.dev.com.ssejavaspring.dtos.UpdateTaskDto;
import giraldi.dev.com.ssejavaspring.services.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    public ResponseEntity<ListTaskDto> insert(@RequestBody InsertTaskDto dto) {
        ListTaskDto result = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ListTaskDto> update(@PathVariable Long id, @RequestBody UpdateTaskDto dto) {
        ListTaskDto result = service.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ListTaskDto> findById(@PathVariable Long id) {
        ListTaskDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
