package com.itasset.management.controller;

import com.itasset.management.model.Asset;
import com.itasset.management.model.User;
import com.itasset.management.service.AssetService;
import com.itasset.management.service.IssueService;
import com.itasset.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private UserService userService;

    @Autowired
    private IssueService issueService;



    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("assets", assetService.getAllAssets());
        return "admin/dashboard";
    }

    @GetMapping("/add-asset")
    public String addAssetPage(Model model) {
        model.addAttribute("asset", new Asset());
        return "admin/add-asset";
    }

    @PostMapping("/add-asset")
    public String addAsset(@ModelAttribute Asset asset) {
        assetService.save(asset);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/add-user")
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/add-user";
    }
    @GetMapping("/profile")
    public String adminProfile() {
        return "admin/profile";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin/dashboard";
    }
    @GetMapping("/search-user")
    public String searchPage() {
        return "admin/search";
    }

    @PostMapping("/search-user")
    public String searchUser(@RequestParam String userId, Model model) {

        User user = userService.findByEmployeeId(userId);

        model.addAttribute("user", user);

        return "admin/search";
    }
    @GetMapping("/issues")
    public String viewIssues(Model model) {

        model.addAttribute("issues", issueService.getAllIssues());

        return "admin/issues";
    }

}


