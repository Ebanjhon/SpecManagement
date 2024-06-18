/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.DTO.CurrentUserDTO;
import com.eban.components.EmailUtil;
import com.eban.components.JwtService;
import com.eban.pojo.User;
import com.eban.services.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nmau4
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private BCryptPasswordEncoder passswordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
// api tạo user

    @PostMapping(path = "/users/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        if (this.userService.getUserByUsername(params.get("username")) == null) {
            return new ResponseEntity<>("Tài khoản đã tồn tại!", HttpStatus.BAD_REQUEST);
        }

        User u = new User();
        u.setFirstname(params.get("firstName"));
        u.setLastname(params.get("lastName"));
        u.setUsername(params.get("username"));
        String password = params.get("password");
        u.setPassword(this.passswordEncoder.encode(password));
        u.setRole(params.get("role"));
        u.setEmail(params.get("email"));
        u.setGender(params.get("gender"));
        u.setActive(true);
        if (file.length > 0) {
            u.setFile(file[0]);
        }
        return new ResponseEntity<>(u.getAvatar(), HttpStatus.CREATED);

//        this.userService.addUser(u);
        // Gửi email thông báo
//        String to = u.getEmail();
//        String subject = "Tạo tài khoản thành công";
//        String text = "<p>Chào " + u.getFirstname() + " " + u.getLastname() + ",</p>"
//                + "<p>Tài khoản của bạn đã được tạo thành công với thông tin như sau:</p>"
//                + "<ul>"
//                + "<li>Tên đăng nhập: " + u.getUsername() + "</li>"
//                + "<li>Email: " + u.getEmail() + "</li>"
//                + "<li>Vai trò: " + u.getRole() + "</li>"
//                + "</ul>"
//                + "<p>Đề nghị cập nhât những thông tin còn thiếu sau khi đăng nhâp.</p>"
//                + "<p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.</p>";
//
//        EmailUtil.sendEmail(to, subject, text);
//        return new ResponseEntity<>("Tạo tài khoản thành công!", HttpStatus.CREATED);
    }

    // Chỉnh sửa User 
    @PutMapping(path = "/users/update", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestParam Map<String, String> params, @RequestPart(required = false) MultipartFile[] file) {
        User u = this.userService.getUserByUsername(params.get("username"));
        if (u != null) {
            u.setFirstname(params.get("firstName"));
            u.setLastname(params.get("lastName"));
            if (params.containsKey("password")) {
                u.setPassword(this.passswordEncoder.encode(params.get("password")));
            }
            u.setRole(params.get("role"));
            u.setEmail(params.get("email"));
            u.setGender(params.get("gender"));
            if (file != null && file.length > 0) {
                u.setFile(file[0]);
            }
            this.userService.updateUser(u);
        }
    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Thông tin đăng nhập không chính xác!", HttpStatus.BAD_REQUEST);
    }

    // lấy thông tin user hiện tại
    @GetMapping(path = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<CurrentUserDTO> getCurrentUser(Principal p) {
        User u = this.userService.getUserByUsername(p.getName());
        CurrentUserDTO user = new CurrentUserDTO(
                u.getIdUser(),
                u.getUsername(),
                u.getFirstname(),
                u.getLastname(),
                u.getDateOfBirth(),
                u.getGender(),
                u.getEmail(),
                u.getAddress(),
                u.getPhone(),
                u.getRole(),
                u.getActive(),
                u.getAvatar(),
                u.getCoin()
        );
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // lấy user byid
    @GetMapping("/author/{id}")
    @CrossOrigin // cho phép tất cả các domain truy cập
    public ResponseEntity<CurrentUserDTO> getAuthor(@PathVariable(value = "id") int id) {
        User u = this.userService.getUserById(id);
        CurrentUserDTO user = new CurrentUserDTO(
                u.getIdUser(),
                u.getUsername(),
                u.getFirstname(),
                u.getLastname(),
                u.getDateOfBirth(),
                u.getGender(),
                u.getEmail(),
                u.getAddress(),
                u.getPhone(),
                u.getRole(),
                u.getActive(),
                u.getAvatar(),
                u.getCoin()
        );
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
