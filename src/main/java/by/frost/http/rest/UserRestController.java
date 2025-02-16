package by.frost.http.rest;

import by.frost.dto.UserCreateEditDto;
import by.frost.dto.UserReadDto;
import by.frost.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@RestController
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public List<UserReadDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Integer id) {
        return userService.findById(id)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Validated @RequestBody UserCreateEditDto userDto) {
        return  userService.create(userDto);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Integer id,
                         @Validated @RequestBody UserCreateEditDto userDto) {
        return userService.update(id, userDto)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
