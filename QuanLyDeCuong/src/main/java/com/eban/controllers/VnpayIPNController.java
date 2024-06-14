//package com.eban.controllers;
//
//import com.eban.configs.VnpayConfig;
//import com.eban.pojo.User;
//import com.eban.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//public class VnpayIPNController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/ipn")
//    public String ipnReturn(HttpServletRequest request, @RequestParam("vnp_TxnRef") String userId) {
//        Map<String, String> fields = new HashMap<>();
//        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
//            String fieldName = params.nextElement();
//            String fieldValue = request.getParameter(fieldName);
//            if ((fieldValue != null) && (fieldValue.length() > 0)) {
//                fields.put(fieldName, fieldValue);
//            }
//        }
//
//        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
//        if (fields.containsKey("vnp_SecureHashType")) {
//            fields.remove("vnp_SecureHashType");
//        }
//        if (fields.containsKey("vnp_SecureHash")) {
//            fields.remove("vnp_SecureHash");
//        }
//
//        String signValue = VnpayConfig.hmacSHA512(VnpayConfig.secretKey, VnpayConfig.hashAllFields(fields));
//        if (signValue.equals(vnp_SecureHash)) {
//            boolean checkOrderId = userService.getUserByUsername(userId) != null;
//            boolean checkAmount = true; // Thực hiện kiểm tra số tiền từ vnp_Amount với cơ sở dữ liệu của bạn
//            boolean checkOrderStatus = true; // Kiểm tra trạng thái thanh toán của đơn hàng
//
//            if (checkOrderId) {
//                if (checkAmount) {
//                    if (checkOrderStatus) {
//                        if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
//                            // Code cập nhật trạng thái thanh toán thành công vào cơ sở dữ liệu
//                            return "{\"RspCode\":\"00\",\"Message\":\"Confirm Success\"}";
//                        } else {
//                            // Code cập nhật trạng thái thanh toán thất bại vào cơ sở dữ liệu
//                            return "{\"RspCode\":\"02\",\"Message\":\"Transaction Failed\"}";
//                        }
//                    } else {
//                        return "{\"RspCode\":\"02\",\"Message\":\"Order already confirmed\"}";
//                    }
//                } else {
//                    return "{\"RspCode\":\"04\",\"Message\":\"Invalid Amount\"}";
//                }
//            } else {
//                return "{\"RspCode\":\"01\",\"Message\":\"Order not Found\"}";
//            }
//        } else {
//            return "{\"RspCode\":\"97\",\"Message\":\"Invalid Checksum\"}";
//        }
//    }
//}
