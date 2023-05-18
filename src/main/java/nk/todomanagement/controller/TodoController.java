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

    @PostMapping //add todo - only admin -----
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        TodoDto savedTodoDto=todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);

    }
    @GetMapping("{id}")//get todo : admin and user
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){

        TodoDto todoDto=todoService.getTodo(id);
        return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }

    @GetMapping //get all todo : admin and user
    public ResponseEntity<List<TodoDto>> getAllTodo(){

        List<TodoDto> todoDtoList = todoService.getAllTodo();
        return new ResponseEntity<>(todoDtoList,HttpStatus.OK);

    }

    @PutMapping("/{id}")//update todo : only admin -------------
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id,@RequestBody TodoDto todoDto){
        return  ResponseEntity.ok(todoService.updateTodo(id,todoDto));
    }

    @DeleteMapping("{id}") // delete todo : only admin
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("user deleted");
    }

    @PatchMapping("{id}/completed")// completed todo : user and admin
    public ResponseEntity<TodoDto> completedTodo(@PathVariable Long id){
        TodoDto todoDto=todoService.completedTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    @PatchMapping("{id}/pending")//pending todo : admin and user
    public ResponseEntity<TodoDto> pendingTodo(@PathVariable Long id){
        TodoDto todoDto=todoService.pendingTodo(id);
        return ResponseEntity.ok(todoDto);
    }
}
