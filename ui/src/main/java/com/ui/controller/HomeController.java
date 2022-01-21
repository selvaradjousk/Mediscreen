package com.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The Class HomeController.
 */
@Controller
public class HomeController {



    /**
     * Gets the home page.
     *
     * @return the home page
     */
    @GetMapping({"/"})
    public String getHomePage() {

        return "home";
    }


}
