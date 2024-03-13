package org.example.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ItemController {
//  save, delete, edit, get, getList, sort
//  add image in item, edit image in item, delete image in item
    @GetMapping("/save")
    public String saveItem() {
        return "/shop/itemAdd";
    }
    @GetMapping("/edit/{itemId}")
    public String editItem() {
        return"/shop/itemEdit";
    }
    @GetMapping("/detail/{itemId}")
    public String detailItem() {
        return "/shop/itemDetail";
    }
    @GetMapping("/purchase/{itemId}")
    public String purchaseItem() {
        return "/shop/order";
    }
    @GetMapping("/postcode")
    public String searchPostCode() {
        return "/shop/postcode";
    }
    @GetMapping(value = {"/list", "/list/{category}"})
    public String getListItem(@PathVariable(required = false) int categoryId) {
        if (categoryId == 0) {
            return "";
        } else {
            return "";
        }
    }
}
