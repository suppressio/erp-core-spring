package net.suppressio.core.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.suppressio.core.demo.models.DemoContent;
import net.suppressio.core.demo.repositories.DemoContentRepository;
import net.suppressio.core.login.model.User;
import net.suppressio.core.login.services.CustomUserDetailsService;

public class DemoContentController {
	
    @Autowired
    private CustomUserDetailsService userService;
    
    @Autowired
    private DemoContentRepository demoRepository;
    
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public ModelAndView demo() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("demo", demoRepository.findAll());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("demo");
        return modelAndView;
    }

    @RequestMapping("/demo/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @RequestMapping("/demo/save")
    public String save(@RequestParam String content) {
        DemoContent demo = new DemoContent();
        demo.setContent(content);
        demo.setUpdated(new Date());
        demoRepository.save(demo);

        return "redirect:/notes/show/" + demo.getId();
    }

    @RequestMapping("/demo/show/{id}")
    public ModelAndView show(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.addObject("note", demoRepository.findById(id).orElse(null));
        modelAndView.setViewName("show");
        return modelAndView;
    }

    @RequestMapping("/demo/delete")
    public String delete(@RequestParam Long id) {
        DemoContent demo = demoRepository.findById(id).orElse(null);
        demoRepository.delete(demo);

        return "redirect:/notes";
    }

    @RequestMapping("/demo/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.addObject("demo", demoRepository.findById(id).orElse(null));
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @RequestMapping("/demo/update")
    public String update(@RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        DemoContent demo = demoRepository.findById(id).orElse(null);
        demo.setContent(content);
        demo.setUpdated(new Date());
        demoRepository.save(demo);

        return "redirect:/demo/show/" + demo.getId();
    }
}
