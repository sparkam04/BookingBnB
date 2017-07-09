package com.netcracker.edu.project.controller;

        import com.netcracker.edu.project.model.User;
        import com.netcracker.edu.project.service.SecurityServise;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.io.IOException;
        import java.util.Map;

@RestController
public class AuthorizationController {

    @Autowired
    private SecurityServise securityServise;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> createUser(@RequestBody User user) {
        return securityServise.registerUser(user);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestParam String email, @RequestParam String password) throws IOException {
        return securityServise.login(email, password);
    }

    @RequestMapping("/change/{id}/{firstName}/{lastName}/{phone}/{email}/{newPassword}/{oldPassword}")
    public Map<String, Object> updateUser(@PathVariable Long id, @PathVariable String firstName,
                                          @PathVariable String lastName, @PathVariable String phone,
                                          @PathVariable String email, @PathVariable String newPassword,
                                          @PathVariable String oldPassword) throws IOException {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPass(newPassword);
        return securityServise.updateUser(user,oldPassword);
    }
}
