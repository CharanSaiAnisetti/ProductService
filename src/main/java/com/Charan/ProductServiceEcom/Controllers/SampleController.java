package com.Charan.ProductServiceEcom.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// this class will be hosting a set of http API's
@RestController // indicating that this is a Special class
@RequestMapping("/say") // "/say" decides which class to get called when we have multiple classes
public class SampleController {

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name){
        return "Hellooooo" + name;
    }

    @GetMapping("/bye")
    public String sayBye(){
        return "ba byeeee";
    }

}
