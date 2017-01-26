package com.demo.levenshtein.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 1/25/17.
 */
@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String getIndex() {
        return "index";
    }

}
