package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.example.config.oauth2.PrincipalDetails;
import org.example.model.dto.shop.AddressDto;
import org.example.model.dto.shop.CommentDto;
import org.example.model.dto.shop.ImageFileDto;
import org.example.model.dto.shop.ItemDto;
import org.example.model.entity.shop.Item;
import org.example.model.entity.shop.ImageFile;
import org.example.service.ImageFileService;
import org.example.service.ItemService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/shop/api")
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;
    private final UserService userService;
    private final ImageFileService imageFileService;

    public List<ItemDto.Response> makeTestCase() {
        List<ItemDto.Response> testList = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            ItemDto.Response response = new ItemDto.Response();
            LocalDateTime n = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = n.format(formatter);

            response.setItemId(i);
            response.setTitle("Test" + i);
            response.setExplain("Explain" + i);
            response.setSeller("Seller" + i);
            response.setCreatedAt(formattedDateTime);

            testList.add(response);
        }
        return testList;
    }

    @GetMapping("/item/list/byNo/{orderType}")
    public ResponseEntity<?> getListOrderByNo(@PathVariable(value = "orderType") int orderType) {
        List<ItemDto.Response> testList = new ArrayList<>();
        if (orderType == 0) {
            testList = makeTestCase();
        } else if (orderType == 1) {
            testList = makeTestCase();
            Collections.reverse(testList);
        }
        return ResponseEntity.ok().body(testList);
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable int itemId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        itemService.deleteItem(itemId, principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/detail/{itemId}")
    public ResponseEntity<?> detailItem(@PathVariable(value = "itemId") int itemId) {

        List<ItemDto.Response> testList = new ArrayList<>();
        ItemDto.Response testResponse = new ItemDto.Response();

        List<ImageFileDto.Response> testImgList = new ArrayList<>();
        ImageFileDto.Response testImg = new ImageFileDto.Response();
        testImg.setFilepath("/static/img/image-0.png");
        testImgList.add(testImg);

        testResponse.setItemId(itemId);
        testResponse.setImageFiles(testImgList);
        testResponse.setTitle("test");
        testResponse.setExplain("explain");
        testResponse.setLikes(10);
        testResponse.setHits(10);
        testResponse.setPrice(1000);
        testResponse.setSeller("tester");
        testResponse.setStock(100);

        testList.add(testResponse);


        return ResponseEntity.ok(testList);
    }

    @PutMapping("/edit/{itemId}")
    public ResponseEntity<?> editItem(@PathVariable int itemId,
                                      @RequestPart(value = "itemRequest") ItemDto.Request itemDto,
                                      @RequestPart(value = "plusFile") List<MultipartFile> plusFile,
                                      @RequestPart(value = "removeFile") List<String> rmFile,
                                      @AuthenticationPrincipal PrincipalDetails principalDetails) {
        itemService.editItem(itemId, itemDto, plusFile, rmFile, principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/item/basket/{id}")
    public void gotoBasket(@PathVariable(value = "id") int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("go basket");
        // userService.setBasket(id, principalDetails.getName());
    }

    @GetMapping("/comments/{itemId}")
    public ResponseEntity<?> loadComments(@PathVariable(value = "itemId") int itemId) {
        List<CommentDto.Response> testCommentList = makeTestComment();

        return ResponseEntity.ok(testCommentList);
    }

    private List<CommentDto.Response> makeTestComment() {
        List<CommentDto.Response> testCommentList = new ArrayList<>();

        for (int i = 1; i <= 10; ++i) {
            CommentDto.Response testComment = new CommentDto.Response();
            LocalDateTime n = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:hh:mm");
            String formattedDateTime = n.format(formatter);

            testComment.setCommentId(i);
            testComment.setWriter("tester" + i);
            testComment.setComment("comment test" + i);
            testComment.setParentId(-1);
            testComment.setDepth(0);
            testComment.setLikes(0);
            testComment.setViewStatus(false);
            testComment.setCreatedAt(formattedDateTime);
            testCommentList.add(testComment);
        }
        makeChildComment(testCommentList);
        return testCommentList;
    }

    private void makeChildComment(List<CommentDto.Response> pComment) {
        int k = 1;
        for (int i = 11; i <= 20; ++i) {
            CommentDto.Response testComment = new CommentDto.Response();
            LocalDateTime n = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:hh:mm");
            String formattedDateTime = n.format(formatter);

            testComment.setCommentId(i);
            testComment.setWriter("child test" + i);
            testComment.setComment("comment child test" + i);
            testComment.setParentId(k++);
            testComment.setDepth(1);
            testComment.setLikes(0);
            testComment.setViewStatus(false);
            testComment.setCreatedAt(formattedDateTime);
            pComment.add(testComment);
        }
        makeChildChildComment(pComment);
    }

    private void makeChildChildComment(List<CommentDto.Response> pComment) {

        int k = 11;
        for (int i = 21; i <= 30; ++i) {
            CommentDto.Response testComment = new CommentDto.Response();
            LocalDateTime n = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:hh:mm");
            String formattedDateTime = n.format(formatter);

            testComment.setCommentId(i);
            testComment.setWriter("child test" + i);
            testComment.setComment("comment child child test" + i);
            testComment.setParentId(k++);
            testComment.setDepth(2);
            testComment.setLikes(0);
            testComment.setViewStatus(false);
            testComment.setCreatedAt(formattedDateTime);
            pComment.add(testComment);
        }
    }

    @PostMapping("/address")
    public void getAddress(@RequestBody AddressDto.Request address) {
        System.out.println("postcode" + address.getPostcode());
        System.out.println("road" + address.getRoadAddress());
        System.out.println("jibun" + address.getJibunAddress());
        System.out.println("detail" + address.getDetailAddress());
    }
}
