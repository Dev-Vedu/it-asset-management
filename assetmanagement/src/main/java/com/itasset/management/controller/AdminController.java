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

    // DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("assets", assetService.getAllAssets());
        return "admin/dashboard";
    }

    //  ADD ASSET PAGE
    @GetMapping("/add-asset")
    public String addAssetPage(Model model) {
        model.addAttribute("asset", new Asset());
        return "admin/add-asset";
    }

    // ✅ SAVE ASSET
    @PostMapping("/add-asset")
    public String addAsset(@ModelAttribute Asset asset) {
        assetService.save(asset);
        return "redirect:/admin/dashboard";
    }

    // ✅ VIEW ALL ASSETS
    @GetMapping("/assets")
    public String viewAssets(Model model) {
        model.addAttribute("assets", assetService.getAllAssets());
        return "admin/assets";
    }

    // ✅ DELETE ASSET
    @GetMapping("/delete-asset/{id}")
    public String deleteAsset(@PathVariable Long id) {
        assetService.delete(id);
        return "redirect:/admin/dashboard";
    }

    // ✅ EDIT ASSET PAGE
    @GetMapping("/edit-asset/{id}")
    public String editAsset(@PathVariable Long id, Model model) {
        model.addAttribute("asset", assetService.getById(id));
        return "admin/edit-asset";
    }

    // ✅ UPDATE ASSET
    @PostMapping("/update-asset")
    public String updateAsset(@ModelAttribute Asset asset) {
        assetService.update(asset);
        return "redirect:/admin/dashboard";
    }

    // ✅ ADD USER PAGE
    @GetMapping("/add-user")
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/add-user";
    }

    // ✅ SAVE USER
    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin/dashboard";
    }

    // ✅ SEARCH USER PAGE
    @GetMapping("/search-user")
    public String searchPage() {
        return "admin/search";
    }

    // ✅ SEARCH USER RESULT
    @PostMapping("/search-user")
    public String searchUser(@RequestParam String userId, Model model) {

        User user = userService.findByEmployeeId(userId);
        model.addAttribute("user", user);

        return "admin/search";
    }

    // ✅ VIEW ALL ISSUES
    @GetMapping("/issues")
    public String viewIssues(Model model) {
        model.addAttribute("issues", issueService.getAllIssues());
        return "admin/issues";
    }

    // ✅ PROFILE
    @GetMapping("/profile")
    public String adminProfile() {
        return "admin/profile";
    }
}
