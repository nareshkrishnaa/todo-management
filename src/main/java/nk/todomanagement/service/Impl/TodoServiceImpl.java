package nk.todomanagement.service.Impl;

import lombok.AllArgsConstructor;
import nk.todomanagement.Exception.ResourceNotFoundException;
import nk.todomanagement.dto.TodoDto;
import nk.todomanagement.entity.Todo;
import nk.todomanagement.repo.TodoRepository;
import nk.todomanagement.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;


    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);

        Todo savedTodo= todoRepository.save(todo);

        return modelMapper.map(savedTodo,TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {

        Optional<Todo> todo = Optional.ofNullable(todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id")));

        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodo() {
       List<Todo> todoList= todoRepository.findAll();
       List<TodoDto> todoDtoList = new ArrayList<>();
       todoList.forEach((n)->todoDtoList.add(modelMapper.map(n,TodoDto.class)));

       if(todoList.isEmpty()){
           throw new ResourceNotFoundException("List is Empty");
       }else
        return todoDtoList;
    }

    @Override
    public TodoDto updateTodo(Long id,TodoDto todoDto) {
         Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No such task exist"));
         Todo updatedTodo= new Todo(todoDto.getId(),todoDto.getTitle(),todoDto.getDescription(),todoDto.isCompleted());
         todoRepository.save(updatedTodo);

         return modelMapper.map(updatedTodo,TodoDto.class);

    }

    @Override
    public void deleteTodo(Long id) {

        Todo todo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No such item exists"));

        todoRepository.deleteById(id);

    }

    @Override
    public TodoDto completedTodo(Long id) {
        Todo todo= todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No such item exists"));
        todo.setCompleted(true);
        todo=todoRepository.save(todo);


        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public TodoDto pendingTodo(Long id) {
        Todo todo= todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No such item exists"));
        todo.setCompleted(false);
        todo=todoRepository.save(todo);


        return modelMapper.map(todo,TodoDto.class);
    }
}
