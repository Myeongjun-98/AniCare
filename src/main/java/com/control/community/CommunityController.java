package com.control.community;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {

    @GetMapping("/community/commain")
    public String commainPage(Model model){

        return "community/commain";
    }
}
