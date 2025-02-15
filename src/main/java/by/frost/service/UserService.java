package by.frost.service;

import by.frost.database.entity.User;
import by.frost.database.repository.UserRepository;
import by.frost.dto.UserCreateEditDto;
import by.frost.dto.UserReadDto;
import by.frost.mapper.UserMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Transactional(readOnly=true)
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id).map(userMapper::mapToUserReadDto);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserReadDto)
                .toList();
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(uDto ->userMapper.mapToUser(uDto))
                .map(user -> userRepository.save(user))
                .map(user -> userMapper.mapToUserReadDto(user))
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Integer id, UserCreateEditDto userDto) {
        Optional<UserReadDto> userReadDto = userRepository.findById(id)
                .map(entity -> userMapper.mapToUser(userDto, entity))
                .map(entity -> userRepository.saveAndFlush(entity))
                .map(entity -> userMapper.mapToUserReadDto(entity));
        return userReadDto;
    }

    @Transactional
    public Boolean delete (Integer id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
