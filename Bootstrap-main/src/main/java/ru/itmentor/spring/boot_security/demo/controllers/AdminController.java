package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.models.Role;
import ru.itmentor.spring.boot_security.demo.models.Users;
import ru.itmentor.spring.boot_security.demo.repositories.RoleRepository;
import ru.itmentor.spring.boot_security.demo.security.UsersDetails;
import ru.itmentor.spring.boot_security.demo.services.UsersServices;

import java.util.List;

@RestController
public class AdminController {
    private final UsersServices usersServices;
    private final RoleRepository roleRepository;


    public AdminController(UsersServices usersServices, RoleRepository roleRepository) {
        this.usersServices = usersServices;
        this.roleRepository = roleRepository;
    }
    @GetMapping("/admin/all")
    public List<Users> getAllUsers() {
        return usersServices.findAllUsers();
    }

    @GetMapping("/admin/user")
    public Users getUser(Authentication authentication) {
        UsersDetails user =(UsersDetails)authentication.getPrincipal();
        return user.getUsers();
    }

    @GetMapping("/admin/{id}")
    public Users getUserById(@PathVariable("id") Integer id){
        return usersServices.findUsersById(id).orElse(null);
    }

    @GetMapping("/admin/roles")
    public List<Role> getUserById(){
        return roleRepository.findAll();
    }

    @PatchMapping("/admin/edit/{id}")
    public Users editUser(@RequestBody Users editUser,
                          @PathVariable("id") Integer id){
        usersServices.updateUserByIdAndUsers(editUser,id);
        return usersServices.findUsersById(id).orElse(null);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        usersServices.deleteUser(id);
    }

    @PostMapping("/admin/save")
    public Users saveUser(@RequestBody Users user){
        usersServices.saveUser(user);
        return user;
    }

    @GetMapping("/user/this")
    public Users getAuthUser(Authentication authentication) {
        UsersDetails user =(UsersDetails)authentication.getPrincipal();
        return user.getUsers();
    }
}
