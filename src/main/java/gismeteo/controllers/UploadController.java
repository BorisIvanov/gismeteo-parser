package gismeteo.controllers;

import gismeteo.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class UploadController {

    private UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/upload/{id}", method = RequestMethod.GET)
    public ModelAndView upload(@PathVariable int id) throws IOException {
        uploadService.get(id);
        return new ModelAndView("index");
    }
}
