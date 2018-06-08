package com.kaitait.springfox;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FooController {

    @ApiOperation(value = "Foo things. Needs authentication.", notes = "Will do foo")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 403, message = "Not Allowed"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 200, message = "All good")
    })
    @ResponseBody
    @RequestMapping(path = "/foo", method = RequestMethod.GET)
    public String foo() {

        return "foo foo foo";
    }

    @ApiOperation(value = "Bar things. Needs authentication.", notes = "Will do bar")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 403, message = "Not Allowed"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 200, message = "All good")
    })
    @ResponseBody
    @RequestMapping(path = "/bar", method = RequestMethod.GET)
    public String bar() {

        return "bar bar bar";
    }

    @ApiOperation(value = "baz things. Does not need authentication.", notes = "Will do baz")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 403, message = "Not Allowed"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 200, message = "All good")
    })
    @ResponseBody
    @RequestMapping(path = "/baz", method = RequestMethod.GET)
    public String baz() {

        return "baz baz baz!";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String ui() {

        return "redirect:swagger-ui.html";
    }

}
