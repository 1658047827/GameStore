package com.fishandchips.web;

import com.fishandchips.pojo.*;
import com.fishandchips.service.CartItemService;
import com.fishandchips.service.OrderService;
import com.fishandchips.service.OwnGameService;
import com.fishandchips.service.impl.CartItemServiceImpl;
import com.fishandchips.service.impl.OrderServiceImpl;
import com.fishandchips.service.impl.OwnGameServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends ViewBaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    private OwnGameService ownGameService = new OwnGameServiceImpl();
    private CartItemService cartItemService = new CartItemServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userId
        Integer userId = (Integer) req.getSession().getAttribute("loggedId");
        //调用方法生成订单
        String orderNo = orderService.createOrder(cart, userId);
        //获取订单号
        req.setAttribute("orderNo", orderNo);
        //更新用户拥有的游戏
        ownGameService.addOwnGame(userId, cart);

        super.processTemplate("WEB-INF/pages/cart/checkout_success", req, resp);
    }

    protected void showAllNormalOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<Order> allNormalOrderList = orderService.showAllNormalOrders();
        req.getSession().setAttribute("allNormalOrderList", allNormalOrderList);

        //前往管理员订单管理页面
        super.processTemplate("WEB-INF/pages/order_admin_page/order_admin", req, resp);
    }

    protected void showAllRefundingOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<Order> refundingOrders = orderService.showAllRefundingOrders();
        req.getSession().setAttribute("allRefundingOrderList", refundingOrders);

        //前往管理员订单管理页面
        req.setAttribute("acceptRefundResult", "0");
        super.processTemplate("WEB-INF/pages/order_admin_page/order_admin_refunding", req, resp);
    }

    protected void showAllRefundedOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<Order> refundedOrders = orderService.showAllRefundedOrders();
        req.getSession().setAttribute("allRefundedOrderList", refundedOrders);

        //前往管理员订单管理页面

        super.processTemplate("WEB-INF/pages/order_admin_page/order_admin_refunded", req, resp);
    }

    protected void showNormalOrdersByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //获取userId
        Integer userId = (Integer) req.getSession().getAttribute("loggedId");
        List<Order> myOrderList = orderService.showMyNormalOrders(userId);
        req.getSession().setAttribute("myOrderList", myOrderList);

        req.setAttribute("applyRefundResult", "0");
        super.processTemplate("WEB-INF/pages/order/myorders", req, resp);
    }

    private void showRefundingOrdersByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Integer userId = (Integer) req.getSession().getAttribute("loggedId");
        List<Order> myRefundingOrders = orderService.showMyRefundingOrders(userId);
        req.getSession().setAttribute("myRefundingOrders", myRefundingOrders);

        req.setAttribute("cancelRefundResult","0");
        super.processTemplate("WEB-INF/pages/order/myorders_refunding", req, resp);
    }

    protected void showRefundedOrdersByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Integer userId = (Integer) req.getSession().getAttribute("loggedId");
        List<Order> myRefundedOrders = orderService.showMyRefundedOrders(userId);
        req.getSession().setAttribute("myRefundedOrders", myRefundedOrders);

        super.processTemplate("WEB-INF/pages/order/myorders_refunded", req, resp);
    }

    private void applyRefund(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String orderNo = req.getParameter("orderNo");

        if (orderService.getOrderByOrderNo(orderNo).getOrderStatus() != 0) {
            req.setAttribute("applyRefundResult", "1");
        } else {
            req.setAttribute("applyRefundResult", "2");
            orderService.applyRefund(orderNo);
        }

        //获取userId
        Integer userId = (Integer) req.getSession().getAttribute("loggedId");
        List<Order> myOrderList = orderService.showMyNormalOrders(userId);
        req.getSession().setAttribute("myOrderList", myOrderList);

        super.processTemplate("WEB-INF/pages/order/myorders", req, resp);
    }

    private void cancelRefund(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String orderNo = req.getParameter("orderNo");

        if (orderService.getOrderByOrderNo(orderNo).getOrderStatus() != 1) {
            req.setAttribute("cancelRefundResult", "1");
        } else {
            req.setAttribute("cancelRefundResult", "2");
            orderService.cancelRefund(orderNo);
        }

        Integer userId = (Integer) req.getSession().getAttribute("loggedId");
        List<Order> myRefundingOrders = orderService.showMyRefundingOrders(userId);
        req.getSession().setAttribute("myRefundingOrders", myRefundingOrders);

        super.processTemplate("WEB-INF/pages/order/myorders_refunding", req, resp);
    }

    private void acceptRefund(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String orderNo = req.getParameter("orderNo");
        Order order = orderService.getOrderByOrderNo(orderNo);
        if (order.getOrderStatus() != 1) {
            req.setAttribute("acceptRefundResult", "1");
        } else {
            Integer toDeleteId = order.getOrderUser();

            List<OwnGame> ownGameList = new ArrayList<>();
            List<OrderItem> orderItems = orderService.getOrderItemByOrderNo(orderNo);
            for (OrderItem orderItem : orderItems) {
                ownGameList.add(new OwnGame(toDeleteId, orderItem.getGame()));
            }

            req.setAttribute("acceptRefundResult", "2");
            ownGameService.deleteOwnGame(toDeleteId, ownGameList);
            orderService.permitRefund(orderNo);
        }

        List<Order> refundingOrders = orderService.showAllRefundingOrders();
        req.getSession().setAttribute("allRefundingOrderList", refundingOrders);

        //前往管理员订单管理页面
        super.processTemplate("WEB-INF/pages/order_admin_page/order_admin_refunding", req, resp);
    }

    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String orderNo = req.getParameter("orderNo");
        List<OrderItem> orderItemList = orderService.getOrderItemByOrderNo(orderNo);
        req.setAttribute("orderNo", orderNo);
        req.getSession().setAttribute("detailedOrderList", orderItemList);

        super.processTemplate("WEB-INF/pages/order/order_detail", req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("op") != null && req.getParameter("op").equals("toCheckPage")) {
            super.processTemplate("WEB-INF/pages/cart/checkout", req, resp);
        }

        if (req.getParameter("op") != null && req.getParameter("op").equals("toCheckPageSingleItem")) {
            //获取游戏的id和用户id
            int gameId = Integer.parseInt(req.getParameter("gameId"));
            int userId = (int) req.getSession().getAttribute("loggedId");
            //检查游戏是否在已拥有中或者购物车中
            CartItem cartItem = cartItemService.getCartItemByGameIdAndUserId(gameId, userId);
            OwnGame ownGame = ownGameService.getOwnGameByUserIdAndGameId(userId, gameId);

            //根据查询结果，设置提示信息
            if (ownGame != null) {
                //已拥有该游戏
                req.setAttribute("buySingleResult", "1");
                req.setAttribute("addToCartResult", "0");

                super.processTemplate("WEB-INF/pages/game_detail", req, resp);
            } else {
                if (cartItem != null) { //如果在购物车中
                    //把该游戏从购物车中的项删除
                    cartItemService.deleteCartItem(cartItem);
                }

                //前往购买页面
                Cart cart = new Cart(); //购物车

                CartItem newCartItem = new CartItem(null, gameId, userId);
                cart.getCartItemList().add(newCartItem); //购物项加入购物车
                req.getSession().setAttribute("cart", cart); //覆盖session中的购物车

                super.processTemplate("WEB-INF/pages/cart/checkout", req, resp);
            }
        }

        if (req.getParameter("toMyOrders") != null && req.getParameter("toMyOrders").equals("1")) {
            showNormalOrdersByUserId(req, resp);
        }

        if (req.getParameter("toRefundingOrders") != null && req.getParameter("toRefundingOrders").equals("1")) {
            showRefundingOrdersByUserId(req, resp);
        }

        if (req.getParameter("applyRefund") != null && req.getParameter("applyRefund").equals("1")) {
            applyRefund(req, resp);
        }

        if (req.getParameter("cancelRefund") != null && req.getParameter("cancelRefund").equals("1")) {
            cancelRefund(req, resp);
        }

        if (req.getParameter("toRefundedOrders") != null && req.getParameter("toRefundedOrders").equals("1")) {
            showRefundedOrdersByUserId(req, resp);
        }

        if (req.getParameter("adminOrders") != null && req.getParameter("adminOrders").equals("1")) {
            showAllNormalOrders(req, resp);
        }

        if (req.getParameter("adminRefundingOrders") != null && req.getParameter("adminRefundingOrders").equals(
                "1")) {
            showAllRefundingOrders(req, resp);
        }

        if (req.getParameter("adminRefundedOrders") != null && req.getParameter("adminRefundedOrders").equals(
                "1")) {
            showAllRefundedOrders(req, resp);
        }

        if (req.getParameter("acceptRefund") != null && req.getParameter("acceptRefund").equals("1")) {
            acceptRefund(req, resp);
        }

        if (req.getParameter("createOrder") != null && req.getParameter("createOrder").equals("1")) {
            createOrder(req, resp);
        }

        if (req.getParameter("orderDetail") != null && req.getParameter("orderDetail").equals("1")) {
            showOrderDetail(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
