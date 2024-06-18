package com.eban.controllers;

import com.eban.pojo.Oderdc;
import com.eban.services.VNPayService;
import com.eban.services.UserService;
import com.eban.pojo.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api")
public class VNPayController {

    private static final Logger logger = Logger.getLogger(VNPayController.class.getName());

    @Autowired
    private VNPayService vnPayService;

    @Autowired
    private UserService userService;

    @PostMapping("/submitOrder")
    public String submitOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("username") String username,
                              HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/QuanLyDeCuong";
        String vnpayUrl = vnPayService.createOrder(orderTotal, username, baseUrl); // truyền username dưới dạng orderInfo
        return "redirect:" + vnpayUrl;
    }

    @GetMapping("/vnpay-payment")
    public String vnpayReturn(HttpServletRequest request, Model model) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo"); // lấy username từ orderInfo
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        logger.info("Payment Status: " + paymentStatus);
        logger.info("Username: " + orderInfo); // Log username lấy từ orderInfo
        logger.info("Total Price: " + totalPrice);

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        if (paymentStatus == 1) {
            User user = userService.getUserByUsername(orderInfo); // Sử dụng username từ orderInfo
            if (user != null) {
                int coins = Integer.parseInt(totalPrice) / 100; // Giả sử mỗi 100 VND = 1 coin
                userService.updateUserCoin(user.getIdUser(), user.getCoin() + coins);
//                logger.info("User ID: " + user.getIdUser());
//                logger.info("Current Coins: " + user.getCoin());
//                logger.info("Coins to be added: " + coins);
//                logger.info("Updated Coins: " + (user.getCoin() + coins));
            } else {
                logger.warning("User not found for username: " + orderInfo);
            }
            return "ordersuccess"; // trả về tên view trong tiles.xml
        } else {
            return "orderfail"; // trả về tên view trong tiles.xml
        }
    }
}
