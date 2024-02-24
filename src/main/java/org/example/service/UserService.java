package org.example.service;

import org.example.model.entity.User;
import org.example.model.entity.board.Comment;
import org.example.model.entity.shop.Item;

import java.util.List;
import java.util.Map;

public interface UserService {
    void save(User user);
    void delete(int userId);
    void edit (User user);
    User getUserById(int userId);
    User getUserByUserName(String userName);
    User getUserByEmail(String email);
    Map<String, Object> getUserPostList(int userId);
    List<Comment> getUserCommentList(int userId);
    Map<String, Object> getUserBasketList(int userId);
    Map<String, Object> getUserPurchaseList(int userId);
}
