package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.AddTodoReqDto;
import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.dto.EditTodoReqDto;
import com.korit.mybatis_study.entity.Todo;
import com.korit.mybatis_study.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public ApiRespDto<?> addTodo(AddTodoReqDto addTodoReqDto) {
        Optional<Todo> foundTodo = todoRepository.findTodoByTitle(addTodoReqDto.getTitle());

        if (foundTodo.isPresent()) {
            return new ApiRespDto<>("failed", "중복된 title", null);
        }

        Optional<Todo> todo = todoRepository.addTodo(addTodoReqDto.toEntity());

        if (todo.isEmpty()) {
            return new ApiRespDto<>("failed", "todo 추가 실패", null);
        }
        return new ApiRespDto<>("success", "todo 추가 성공", todo.get());
    }

    public ApiRespDto<?> getTodoAll() {
        return new ApiRespDto<>("success", "전체 조회", todoRepository.getTodoAll());
    }

    public ApiRespDto<?> getTodoByTodoId(Integer todoId) {
        Optional<Todo> foundTodo = todoRepository.findTodoByTodoId(todoId);

        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않는 게시글", null);
        }

        return new ApiRespDto<>("success", "단건 조회", foundTodo.get());
    }

    public ApiRespDto<?> editTodo(EditTodoReqDto editTodoReqDto) {
        Optional<Todo> foundTodo = todoRepository.findTodoByTodoId(editTodoReqDto.getTodoId());

        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않는 게시글", null);
        }

        int result = todoRepository.editTodo(editTodoReqDto.toEntity());
        if (result != 1) {
            return new ApiRespDto<>("failed", "게시글 수정 실패", null);
        }

        return  new ApiRespDto<>("success", "게시글 수정 완료", null);
    }

    public ApiRespDto<?> removeTodo(Integer todoId) {
        Optional<Todo> foundTodo = todoRepository.findTodoByTodoId(todoId);

        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않는 게시글", null);
        }

        int result = todoRepository.removeTodo(todoId);

        if (result != 1) {
            return new ApiRespDto<>("failed", "게시글 삭제 싪패", null);
        }
        return new ApiRespDto<>("success", "게시글 삭제 완료", null);
    }
}
