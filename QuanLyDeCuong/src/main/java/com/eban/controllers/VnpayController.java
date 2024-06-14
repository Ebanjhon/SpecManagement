//package com.eban.controllers;
//
//import com.eban.configs.VnpayConfig;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//public class VnpayCallbackController {
//
//    @GetMapping("/vnpay_return")
//    public String vnpayReturn(HttpServletRequest request, @RequestParam("vnp_TxnRef") String userId) {
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
//            if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
//                return "GD Thanh cong";
//            } else {
//                return "GD Khong thanh cong";
//            }
//        } else {
//            return "Chu ky khong hop le";
//        }
//    }
//}
