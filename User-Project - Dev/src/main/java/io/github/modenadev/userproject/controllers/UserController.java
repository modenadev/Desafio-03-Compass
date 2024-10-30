package io.github.modenadev.userproject.controllers;

import io.github.modenadev.userproject.data.vo.v1.UserVO;
import io.github.modenadev.userproject.exceptions.BadRequestException;
import io.github.modenadev.userproject.model.User;
import io.github.modenadev.userproject.model.dto.UserRequestDTO;
import io.github.modenadev.userproject.repositories.UserRepository;
import io.github.modenadev.userproject.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints for Managing Users")
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserServices service;

//        @GetMapping()
//    @Operation(summary = "Finds all People", description = "Finds all People",
//            tags = {"People"},
//            responses = {
//                    @ApiResponse(description = "Success", responseCode = "200",
//                            content = {
//                                    @Content(
//                                            mediaType = "application/json",
//                                            array = @ArraySchema(schema = @Schema(implementation = UserVO.class))
//                                    )
//                            }),
//                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
//            }
//    )
//    public List<UserVO> findAll() {
//        return service.findAll();
//    }


@GetMapping(value = "/{id}")
@Operation(summary = "Finds a User", description = "Finds a User",
        tags = {"People"},
        responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                        content = @Content(schema = @Schema(implementation = UserVO.class))
                ),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
)
public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {
    User userVO = service.findById(id);
    return ResponseEntity.ok(userVO);
}


    @PostMapping()
    @Operation(summary = "Adds a new User",
            description = "Adds a new User by passing in a JSON, XML or YML representation of the person!",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )

    public ResponseEntity<User> createUser(@RequestBody UserVO userVO) throws BadRequestException {
        User user = service.create(userVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @PatchMapping(value = "/update-password/{id}")
    @Operation(summary = "Updates a user password", description = "Updates a password using the id",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserVO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<?> updatePassword(@PathVariable(value = "id") Long id, @RequestBody UserRequestDTO request) throws BadRequestException {
         service.update(id, request.getUsername(), request.getOldPassword(), request.getNewPassword());
         return ResponseEntity.noContent().build();
    }



    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a User",
            description = "Deletes a User by passing in a JSON, XML or YML representation of the person!",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
