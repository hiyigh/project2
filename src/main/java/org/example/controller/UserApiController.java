package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.repository.UserRepository;
import org.example.util.exception.ErrorCode;
import org.example.util.validation.annotation.Email;
import org.example.util.validation.annotation.Name;
import org.example.util.validation.annotation.Password;
import org.example.util.validation.validator.UserEmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api")
@Validated
public class UserApiController {
    private final UserRepository userRepository;
    @PostMapping("/join/email/check")
    public ResponseEntity<?> checkEmail(@RequestParam(value="email") @Valid @Email String email){
        if (userRepository.checkDuplicatedEmail(email)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorCode.EMAIL_DUPLICATED);
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/join/name/check")
    public ResponseEntity<?> checkName(@RequestParam(value="name") @Valid @Name String name) {
        if (userRepository.checkDuplicatedName(name)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorCode.NAME_DUPLICATED);
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/join/password/check")
    public ResponseEntity<?> checkPassword(@RequestParam(value="password") @Valid @Password String password) {


        return ResponseEntity.ok().build();
    }
}
