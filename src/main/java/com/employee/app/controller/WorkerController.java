package com.employee.app.controller;

import com.employee.app.entities.Workers;
import com.employee.app.services.WorkerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Log4j2
public class WorkerController {
    WorkerService workerService;

    public WorkerController(WorkerService workerService){
        this.workerService=workerService;
    }

    @RequestMapping(value = "/worker-registration", method = RequestMethod.GET)
    public ModelAndView createUser(){
        ModelAndView model= new ModelAndView();
        model.addObject("/worker-registr");
        return model;
    }

    @RequestMapping(value = "/worker-registration", method = RequestMethod.POST)
    public ModelAndView saveUser(@Valid Workers worker){
        ModelAndView model= new ModelAndView();
        workerService.createWorker(worker);
        model.setViewName("/worker-registr");
        return model;
    }
}
