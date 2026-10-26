package com.app.Service;
import com.app.Repository.UserRepo;
import com.app.dto.AddressDto;
import com.app.dto.UserRequest;
import com.app.dto.UserResponse;
import com.app.models.Address;
import com.app.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public boolean save(UserRequest  userRequest){
       Users users = new Users();
       updateUserFromRequest( users,userRequest);
       userRepo.save(users);
      return true;
    }


    public List<UserResponse> GetAll(){
        return userRepo.findAll().stream().map(this::MaptoUserResponse).collect(Collectors.toList());
    }
    public boolean fetchById(Long id){
        Optional<Users> byId = userRepo.findById(id);
        return byId.isPresent();

    }
    public Boolean deleteById(Long id){

        Optional<Users> byId = userRepo.findById(id);
        if (byId.isPresent()){
            userRepo.delete(byId.get());
            return true;
        }
        return false;

    }
    public boolean updateById(Long id , UserRequest userRequest){
        userRepo.findById(id).map(exitingUser->{
            updateUserFromRequest(exitingUser,userRequest);
            userRepo.save(exitingUser);
            return true;
        });
return false;
    }

    private void updateUserFromRequest(Users users, UserRequest userRequest) {
     users.setFirstName(userRequest.getFirstName());
     users.setLastName(userRequest.getLastName());
     users.setEmail(userRequest.getEmail());
     users.setPhone(userRequest.getPhone());
     if (userRequest.getAddress()!=null){
         Address address = new Address();
         address.setHouseNo(userRequest.getAddress().getHouseNo());
         address.setStreet(userRequest.getAddress().getStreet());
         address.setCity(userRequest.getAddress().getCity());
         address.setState(userRequest.getAddress().getState());
         address.setCountry(userRequest.getAddress().getCountry());
         address.setZipcode(userRequest.getAddress().getZipcode());
         users.setAddress(address);
     }
    }
  private UserResponse MaptoUserResponse(Users users){
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(users.getId()));
        response.setFirstName(users.getFirstName());
        response.setLastName(users.getLastName());
        response.setEmail(users.getEmail());
        response.setPhone(users.getPhone());
        response.setRole(users.getRole());

        if (users.getAddress()!=null){
            AddressDto addressDto =new AddressDto();
            addressDto.setHouseNo(users.getAddress().getHouseNo());
            addressDto.setStreet(users.getAddress().getStreet());
            addressDto.setCity(users.getAddress().getCity());
            addressDto.setState(users.getAddress().getState());
            addressDto.setCountry(users.getAddress().getCountry());
            addressDto.setZipcode(users.getAddress().getZipcode());
            response.setAddress(addressDto);

        }
return response;
  }
    }


