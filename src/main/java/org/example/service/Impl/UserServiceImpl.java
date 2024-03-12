package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.UserDto;
import org.example.model.dto.shop.ImageFileDto;
import org.example.model.entity.User;
import org.example.model.entity.shop.Comment;
import org.example.repository.ImageFileRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageFileRepository imageFileRepository;
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int userId) {
        userRepository.delete(userId);
    }

    @Override
    public void edit(User user) {
        userRepository.edit(user);
    }
    @Override
    public UserDto.Response getUserById(int userId) {
        User user = userRepository.getUserById(userId);
        ImageFileDto.Response imageFile = imageFileRepository.getUserImageByUserId(user.getUserId());
        UserDto.Response userDto = UserDto.toUserDtoResponse(user, imageFile);
        return userDto;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public Map<String, Object> getUserPostList(int userId) {
        return userRepository.getUserPostList(userId);
    }

    @Override
    public List<Comment> getUserCommentList(int userId) {
        return userRepository.getUserCommentList(userId);
    }

    @Override
    public Map<String, Object> getUserBasketList(int userId) {
        return userRepository.getUserBasketList(userId);
    }

    @Override
    public Map<String, Object> getUserPurchaseList(int userId) {
        return userRepository.getUserPurchaseList(userId);
    }

    @Override
    public int getUserIdByEmail(String email) {
        return userRepository.getUserIdByEmail(email);
    }

    @Override
    public void setBasket(int itemId, String userName) {
        userRepository.setBasket(itemId, userName);
    }
}
