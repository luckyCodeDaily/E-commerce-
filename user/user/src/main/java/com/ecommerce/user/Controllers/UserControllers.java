package com.ecommerce.user.Controllers;


import com.ecommerce.user.Dto.UserRequest;
import com.ecommerce.user.Dto.UserResponse;
import com.ecommerce.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api-users")
@RestController
public class UserControllers {

@Autowired
private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return new ResponseEntity<>( userService.GetAll(), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<?> saveUsers(@RequestBody UserRequest userRequest){
        userService.save(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
@GetMapping("/{id}")
    public boolean getById(@PathVariable Long id){
  return  userService.fetchById(id);
}
@PutMapping("/{id}")
public ResponseEntity<String> UpdateByUser(@PathVariable Long id , @RequestBody UserRequest userRequest ){
    boolean update = userService.updateById(id, userRequest);
    if (update){
        return ResponseEntity.ok("here your ");
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);


}

}
