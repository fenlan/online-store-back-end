/**
 * @author： fanzhonghao
 * @date: 18-12-10 14 58
 * @version: 1.0
 * @description:
 */
package com.fenlan.spring.shop.controller.seller;

import com.fenlan.spring.shop.bean.ResponseFormat;
import com.fenlan.spring.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/seller")
public class ManageShopInfo {
    @Autowired
    ShopService shopService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 由卖家名得到商店信息
     * @param sellerName
     * @return
     */
    @GetMapping("/shop/info")
    public ResponseEntity<ResponseFormat> getShopInfo(@RequestParam("sellerName") String sellerName){
        try {
            return new ResponseEntity<>(new ResponseFormat.Builder(new Date(), HttpStatus.OK.value())
                    .error(null)
                    .message("find the shop info")
                    .path(request.getServletPath())
                    .data(shopService.findByUserName(sellerName))
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseFormat.Builder(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .error("find error")
                    .message(e.getLocalizedMessage())
                    .path(request.getServletPath())
                    .data(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
