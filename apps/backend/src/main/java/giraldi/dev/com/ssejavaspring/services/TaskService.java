package giraldi.dev.com.ssejavaspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import giraldi.dev.com.ssejavaspring.dtos.InsertTaskDto;
import giraldi.dev.com.ssejavaspring.dtos.ListTaskDto;
import giraldi.dev.com.ssejavaspring.dtos.UpdateTaskDto;
import giraldi.dev.com.ssejavaspring.entities.Task;
import giraldi.dev.com.ssejavaspring.repositories.TaskRepository;
import giraldi.dev.com.ssejavaspring.services.exceptions.DatabaseException;
import giraldi.dev.com.ssejavaspring.services.exceptions.ResourceNotFoundException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Transactional
    public ListTaskDto insert(InsertTaskDto dto) {
        Task entity = new Task();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        entity = repository.save(entity);

        return new ListTaskDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription());
    }

    @Transactional
    public ListTaskDto update(Long id, UpdateTaskDto dto) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task com id %d não existe".formatted(id)));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        try {
            repository.save(entity);
            return new ListTaskDto(entity.getId(), entity.getName(), entity.getDescription());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade: %s".formatted(e.getMessage()));
        }
    }

    @Transactional(readOnly = true)
    public ListTaskDto findById(Long id) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task com id %d não existe".formatted(id)));
        return new ListTaskDto(entity.getId(), entity.getName(), entity.getDescription());
    }

    public void delete(Long id) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task com id %d não existe".formatted(id)));
        repository.delete(entity);
    }
}
