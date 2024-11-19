package com.pocketdoctor.Controller;

import com.pocketdoctor.model.DoctorData;
import com.pocketdoctor.repository.DoctorRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/doctor")
@CrossOrigin(origins = "*")
public class PageController {


    @Autowired
    private DoctorRepository doctorRepository;


    @GetMapping("/create-password/{username}")
    public String showCreatePasswordPageWithUsername(@PathVariable String username, Model model) {
        String[] usernameParts = username.split("_");
        model.addAttribute("username", username);
        return "newpassword";
    }

    @PostMapping("/create-password/{username}")
    public String createPassword(@PathVariable String username,
                                 @RequestParam String password,
                                 @RequestParam String password2,
                                 Model model) {
        if (!password.equals(password2)) {
            model.addAttribute("username", username);
            model.addAttribute("error", "Passwords do not match.");
            return "newpassword";
        }

        String[] usernameParts = username.split("_");
        //String user = usernameParts[0];
        String hospitalId = usernameParts[usernameParts.length - 1];

        int rowsUpdated = doctorRepository.updatePassword(password, username, hospitalId);
        if (rowsUpdated > 0) {
            return "success";
        } else {
            model.addAttribute("error", "User not found.");
            return "newpassword";
        }
    }



}
