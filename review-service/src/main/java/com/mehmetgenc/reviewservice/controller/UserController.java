package com.mehmetgenc.reviewservice.controller;

import com.mehmetgenc.reviewservice.controller.contract.UserControllerContract;
import com.mehmetgenc.reviewservice.dto.UserDTO;
import com.mehmetgenc.reviewservice.general.KafkaProducerService;
import com.mehmetgenc.reviewservice.general.RestResponse;
import com.mehmetgenc.reviewservice.request.UserSaveRequest;
import com.mehmetgenc.reviewservice.request.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@Tag(name = "User Controller", description = "User Management")
@CrossOrigin("${cross.origin.url}")
public class UserController {
    private final UserControllerContract userControllerContract;
    private final KafkaProducerService kafkaProducerService;

    public UserController(UserControllerContract userControllerContract, KafkaProducerService kafkaProducerService) {
        this.userControllerContract = userControllerContract;
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details")
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody @Valid UserSaveRequest userSaveRequest){
        UserDTO userDto = userControllerContract.save(userSaveRequest);
        kafkaProducerService.sendMessage("infoLog", "User saved with id: " + userDto.id());
        return ResponseEntity.ok(RestResponse.of(userDto));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Find user by ID", description = "Returns a single user based on the provided ID")
    public ResponseEntity<RestResponse<UserDTO>> findById(@PathVariable @Positive Long userId){
        UserDTO userDto = userControllerContract.findById(userId);
        return ResponseEntity.ok(RestResponse.of(userDto));
    }

    @GetMapping
    @Operation(summary = "Find all users", description = "Returns all users")
    public ResponseEntity<RestResponse<List<UserDTO>>> findAll(){
        List<UserDTO> userDTOS = userControllerContract.findAll();
        return ResponseEntity.ok(RestResponse.of(userDTOS));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by ID", description = "Deletes a single user based on the provided ID")
    public ResponseEntity<RestResponse<String>> deleteUser(@PathVariable @Positive Long userId){
        String message = userControllerContract.delete(userId);
        kafkaProducerService.sendMessage("infoLog", "User deleted with id: " + userId);
        return ResponseEntity.ok(RestResponse.of(message));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user by ID", description = "Updates a single user based on the provided ID")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable @Positive Long userId, @RequestBody @Valid UserUpdateRequest userUpdateRequest){
        UserDTO userDto = userControllerContract.update(userId, userUpdateRequest);
        kafkaProducerService.sendMessage("infoLog", "User updated with id: " + userId);
        return ResponseEntity.ok(RestResponse.of(userDto));
    }

    @PostMapping("/saveBatchUsers")
    @Operation(summary = "Create new users", description = "Creates new users with the provided details")
    public ResponseEntity<RestResponse<List<UserDTO>>> saveBatchUsers(@RequestBody List< @Valid UserSaveRequest> userSaveRequestList){
        List<UserDTO> userDtoList = userControllerContract.saveBatch(userSaveRequestList);
        return ResponseEntity.ok(RestResponse.of(userDtoList));
    }

}
