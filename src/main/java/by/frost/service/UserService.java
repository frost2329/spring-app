package by.frost.service;

import by.frost.database.repository.UserRepository;
import by.frost.dto.UserCreateEditDto;
import by.frost.dto.UserReadDto;
import by.frost.mapper.UserMapper;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Transactional(readOnly=true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(entity -> new org.springframework.security.core.userdetails.User(
                        entity.getUsername(),
                        entity.getPassword(),
                        Collections.singleton(entity.getRole())
                )).orElseThrow( () -> new UsernameNotFoundException("User is not found " + username));
    }
}
