package nk.todomanagement.service;

import nk.todomanagement.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long id);
    List<TodoDto> getAllTodo();
    TodoDto updateTodo(Long id,TodoDto todoDto);
    void deleteTodo(Long id);

}
