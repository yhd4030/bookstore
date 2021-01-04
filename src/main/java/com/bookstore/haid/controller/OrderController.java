package com.bookstore.haid.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.bookstore.haid.dto.OrderInfoDTO;
import com.bookstore.haid.model.*;
import com.bookstore.haid.service.AddressService;
import com.bookstore.haid.service.OrderService;
import com.bookstore.haid.service.UserInfoService;
import com.bookstore.haid.utils.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserInfoService userInfoService;

    //订单信息页面
    @RequestMapping("/info")
    public String orderInfo(HttpServletRequest request, Model model) {
        Double countMoney = 0d;
        String username = (String) request.getSession().getAttribute("username");
        List<Address> address = addressService.findAddress(username);
        List<ShopCart> checkBooks = (List<ShopCart>) request.getSession().getAttribute("checkBooks");
        for (ShopCart shopCart : checkBooks) {
            countMoney = countMoney + shopCart.getSubTotal();
        }
        User user = userInfoService.findUserById(username);
        model.addAttribute("user", user);
        model.addAttribute("checkBooks", checkBooks);
        model.addAttribute("countMoney", countMoney);
        model.addAttribute("addressList", address);

        return "orderInfo";
    }

    //订单信息查询方法
    @RequestMapping("/details")
    @ResponseBody
    public Boolean orderDetails(String bookIdStr, HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");
        String[] strings = bookIdStr.split(",");
        List<ShopCart> shopCartList = orderService.selectByCheck(strings, username);
        request.getSession().setAttribute("checkBooks", shopCartList);

        return true;
    }

    //订单提交后支付
    @PostMapping("/commit")
    @ResponseBody
    public Integer commitOrder(Integer addressId, Double OrderTotal, HttpSession session, Model model) {
        System.out.println(addressId);
        List<ShopCart> checkBooks = (List<ShopCart>) session.getAttribute("checkBooks");
        String username = (String) session.getAttribute("username");
        Order buy = orderService.buy(addressId, session, OrderTotal, checkBooks);
        Order order = orderService.findIdByOrderNum(buy.getOrder_num());
        return order.getId();
    }

    @GetMapping("/toPay")
    public String toPay(Integer id, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        OrderInfoDTO orderById = orderService.findOrderById(id, username);
        model.addAttribute("order", orderById);
        return "orderCommit";
    }

    //订单提交后 查看所有的订单
    @RequestMapping("/list")
    public String OrderList(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        List<OrderInfoDTO> orderInfoDTOS = orderService.selectOrderInfo(username);
        model.addAttribute("orderInfoDTOS", orderInfoDTOS);
        System.out.println(new Date());
        return "orderList";
    }

    //从订单列表跳转到订单支付
    @RequestMapping("/orderPay")
    public String toPay(Integer id, HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        OrderInfoDTO orderById = orderService.findOrderById(id, username);
        model.addAttribute("order", orderById);
        return "orderCommit";
    }

    //书籍直接购买
    @RequestMapping("/infoByBuy")
    public String BuyInfo(Integer bookId, HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");
        List<ShopCart> shopCartList = orderService.selectBookMsgByBookId(username, bookId);
        List<Address> address = addressService.findAddress(username);
        model.addAttribute("checkBooks", shopCartList);
        model.addAttribute("addressList", address);
        model.addAttribute("countMoney", shopCartList.get(0).getSubTotal());
        request.getSession().setAttribute("checkBooks", shopCartList);
        return "orderInfo";
    }

    //完成订单
    @RequestMapping("/completeOrder")
    @ResponseBody
    public Boolean completeOrder(Integer id) {
        Boolean aBoolean = orderService.OrderStatusUpdate(id);
        return aBoolean;
    }

    @RequestMapping("/deleteOrder")
    @ResponseBody
    public Boolean deleteOrder(Integer id) {
        return orderService.deleteOrderById(id);
    }

    //支付
    @RequestMapping("/aliPay")
    @ResponseBody
    public String pay(Pay pay) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        //1.封装Rsa签名方式
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.URL,
                alipayConfig.APPID,
                alipayConfig.RSA_PRIVATE_KEY,
                alipayConfig.FORMAT,
                alipayConfig.CHARSET,
                alipayConfig.ALIPAY_PUBLIC_KEY,
                alipayConfig.SIGNTYPE);
        //2.创建Request请求
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        //3.封装传入参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(pay.getOut_trade_no());//商品Id
        model.setSubject(pay.getSubject());//商品名称
        model.setBody(pay.getBody());//商品描述
        model.setProductCode(pay.getProduct_code());//商品码
        model.setTimeoutExpress(pay.getTimeout_express());//支付超时时间
        model.setTotalAmount(pay.getTotal_amount());//商品金额
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.notify_url);
        request.setReturnUrl(AlipayConfig.return_url);
        String form = alipayClient.pageExecute(request).getBody();
        System.out.println(form);
        return form;
    }
}
