package nk.todomanagement.controller;

import lombok.AllArgsConstructor;
import nk.todomanagement.dto.TodoDto;
import nk.todomanagement.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/todo")
public class TodoController {
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        TodoDto savedTodoDto=todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);

    }
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){

        TodoDto todoDto=todoService.getTodo(id);
        return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){

        List<TodoDto> todoDtoList = todoService.getAllTodo();
        return new ResponseEntity<>(todoDtoList,HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id,@RequestBody TodoDto todoDto){
        return  ResponseEntity.ok(todoService.updateTodo(id,todoDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("user deleted");
    }
}
