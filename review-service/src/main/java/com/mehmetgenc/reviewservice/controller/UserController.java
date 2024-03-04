package com.mehmetgenc.reviewservice.controller;

import com.mehmetgenc.reviewservice.controller.contract.UserControllerContract;
import com.mehmetgenc.reviewservice.dto.UserDTO;
import com.mehmetgenc.reviewservice.general.RestResponse;
import com.mehmetgenc.reviewservice.request.UserSaveRequest;
import com.mehmetgenc.reviewservice.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody UserSaveRequest userSaveRequest){
        UserDTO userDto = userControllerContract.save(userSaveRequest);
        return ResponseEntity.ok(RestResponse.of(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<RestResponse<UserDTO>> findById(@PathVariable Long userId){
        UserDTO userDto = userControllerContract.findById(userId);
        return ResponseEntity.ok(RestResponse.of(userDto));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> findAll(){
        List<UserDTO> userDTOS = userControllerContract.findAll();
        return ResponseEntity.ok(RestResponse.of(userDTOS));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<RestResponse<String>> deleteUser(@PathVariable Long userId){
        String message = userControllerContract.delete(userId);
        return ResponseEntity.ok(RestResponse.of(message));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest){
        UserDTO userDto = userControllerContract.update(userId, userUpdateRequest);
        return ResponseEntity.ok(RestResponse.of(userDto));
    }

}
