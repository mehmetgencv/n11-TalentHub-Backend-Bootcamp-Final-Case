package com.mehmetgenc.reviewservice.controller;

import com.mehmetgenc.reviewservice.controller.contract.UserControllerContract;
import com.mehmetgenc.reviewservice.dto.UserDTO;
import com.mehmetgenc.reviewservice.general.RestResponse;
import com.mehmetgenc.reviewservice.request.UserSaveRequest;
import com.mehmetgenc.reviewservice.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    private final UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody @Valid UserSaveRequest userSaveRequest){
        UserDTO userDto = userControllerContract.save(userSaveRequest);
        return ResponseEntity.ok(RestResponse.of(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<RestResponse<UserDTO>> findById(@PathVariable @Positive Long userId){
        UserDTO userDto = userControllerContract.findById(userId);
        return ResponseEntity.ok(RestResponse.of(userDto));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> findAll(){
        List<UserDTO> userDTOS = userControllerContract.findAll();
        return ResponseEntity.ok(RestResponse.of(userDTOS));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<RestResponse<String>> deleteUser(@PathVariable @Positive Long userId){
            String message = userControllerContract.delete(userId);
        return ResponseEntity.ok(RestResponse.of(message));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable @Positive Long userId, @RequestBody @Valid UserUpdateRequest userUpdateRequest){
        UserDTO userDto = userControllerContract.update(userId, userUpdateRequest);
        return ResponseEntity.ok(RestResponse.of(userDto));
    }

    @PostMapping("/saveBatchUsers")
    public ResponseEntity<RestResponse<List<UserDTO>>> saveBatchUsers(@RequestBody List< @Valid UserSaveRequest> userSaveRequestList){
        List<UserDTO> userDtoList = userControllerContract.saveBatch(userSaveRequestList);
        return ResponseEntity.ok(RestResponse.of(userDtoList));
    }

}
