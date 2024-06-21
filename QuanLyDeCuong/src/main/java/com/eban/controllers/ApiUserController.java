/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.DTO.CurrentUserDTO;
import com.eban.DTO.SearchChatDTO;
import com.eban.DTO.UserDTO;
import com.eban.components.EmailUtil;
import com.eban.components.JwtService;
import com.eban.pojo.User;
import com.eban.services.UserService;
import java.security.Principal;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
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
// api tao user

    @PostMapping(path = "/users/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        if (this.userService.getUserByUsername(params.get("username")) != null) {
            return new ResponseEntity<>("Tên người dùng đã có sẳn!", HttpStatus.BAD_REQUEST);
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
        u.setCoin(2);
        if (file.length > 0) {
            u.setFile(file[0]);
        }

        this.userService.addUser(u);

        // Gửi email thông báo
        String to = u.getEmail();
        String subject = "Tao tai khoan thanh cong ";
        String text = "<p>Chào " + u.getFirstname() + " " + u.getLastname() + ",</p>"
                + "<p>Tài khoản của bạn đã được tạo thành công với thông tin như sau:</p>"
                + "<ul>"
                + "<li>Tên đăng nhập: " + u.getUsername() + "</li>"
                + "<li>Email: " + u.getEmail() + "</li>"
                + "<li>Vai trò: " + u.getRole() + "</li>"
                + "</ul>"
                + "<p>Đề nghị cập nhât những thông tin còn thiếu sau khi đăng nhâp.</p>"
                + "<p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.</p>";

        EmailUtil.sendEmail(to, subject, text);
        return new ResponseEntity<>("tạo tài khoản thành công", HttpStatus.CREATED);
    }

    // Chỉnh sửa User 
    @PostMapping(path = "/users/update/{userId}", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateUser(
            @RequestParam Map<String, String> params,
            @RequestPart(required = false) MultipartFile[] file,
            @PathVariable("userId") int userId
    ) {
        User u = this.userService.getUserById(userId);
        if (u != null) {
            if (params.containsKey("firstName")) {
                u.setFirstname(params.get("firstName"));
            }
            if (params.containsKey("lastName")) {
                u.setLastname(params.get("lastName"));
            }
            if (params.containsKey("address")) {
                u.setAddress(params.get("address"));
            }
            if (params.containsKey("email")) {
                u.setEmail(params.get("email"));
            }
            if (params.containsKey("phone")) {
                u.setEmail(params.get("phone"));
            }
            if (params.containsKey("dateOfBirth")) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date date = formatter.parse(params.get("dateOfBirth"));
                    u.setDateOfBirth(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return ResponseEntity.badRequest().body("Dinh dang ngay sai");
                }
            }
            if (params.containsKey("gender")) {
                u.setGender(params.get("gender"));
            }
            if (file != null && file.length > 0) {
                u.setFile(file[0]);
            }
            this.userService.updateUser(u);
            return ResponseEntity.ok("User updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
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

    //API tạo user do admin tạo 
    @PostMapping(path = "/users/createUserBy/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public String createUserByAmin(@RequestParam Map<String, String> params, @RequestPart(required = false) MultipartFile[] file, Model model) {
        if (this.userService.getUserByUsername(params.get("username")) != null) {
            model.addAttribute("error", "Tên người dùng đã có sẵn!");
            return "UsernameD da ton tai";
        }
        User u = new User();
        u.setFirstname(params.get("firstName"));
        u.setLastname(params.get("lastName"));
        u.setUsername(params.get("username"));
        String password = RandomStringUtils.randomAlphanumeric(8);
        u.setPassword(this.passswordEncoder.encode(password));
        u.setRole(params.get("role"));
        u.setEmail(params.get("email"));
        u.setGender(params.get("gender"));
        u.setActive(true);
        u.setCoin(2);
        if (file != null && file.length > 0) {
            u.setFile(file[0]);
        }

        this.userService.addUserByAdmin(u);

        // Gửi email thông báo
        String to = u.getEmail();
        String subject = "Ban da duoc cap tai khoan thanh cong moi dang nhap vao he thong và cap nhat thong tin";
        String text = "<p>Chào " + u.getFirstname() + " " + u.getLastname() + ",</p>"
                + "<p>Tài khoản của bạn đã được tạo thành công với thông tin như sau:</p>"
                + "<ul>"
                + "<li>Tên đăng nhập: " + u.getUsername() + "</li>"
                + "<li>Email: " + u.getEmail() + "</li>"
                + "<li>Vai trò: " + u.getRole() + "</li>"
                + "<li>Mật khẩu: " + password + "</li>"
                + "</ul>"
                + "<p>Đề nghị cập nhât mật khẩu và những thông tin còn thiếu sau khi đăng nhâp.</p>"
                + "<p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.</p>";

        EmailUtil.sendEmail(to, subject, text);
        model.addAttribute("successMessage", "Tạo tài khoản thành công!");
        return "Tao tai khoan thanh cong";
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

    ///APi đổi mk truyền 
//    {
//    "oldPassword": "old_password_value",
//    "newPassword": "new_password_value"
//    }
//    
//    200 OK: Khi mật khẩu được thay đổi thành công.
//    400 Bad Request: Khi không có mật khẩu cũ hoặc mật khẩu mới trong request body.
//    401 Unauthorized: Khi mật khẩu cũ không đúng.
    @PostMapping(path = "/users/change-password/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<String> changePassword(@PathVariable("userId") int userId,@RequestBody Map<String, String> request) {

        // Log thông tin nhận được
        System.out.println("UserId: " + userId);
        System.out.println("Request: " + request);

        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");

        if (oldPassword == null || oldPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("Old password is missing");
        }

        if (newPassword == null || newPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("New password is missing");
        }

        boolean isChanged = userService.changePassword(userId, oldPassword, newPassword);

        if (isChanged) {
            return new ResponseEntity<>("tạo thành công", HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Old password is incorrect");
        }
    }
}
