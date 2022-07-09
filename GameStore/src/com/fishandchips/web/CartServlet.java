package com.fishandchips.web;

import com.fishandchips.pojo.*;
import com.fishandchips.service.CartItemService;
import com.fishandchips.service.GameService;
import com.fishandchips.service.OwnGameService;
import com.fishandchips.service.impl.CartItemServiceImpl;
import com.fishandchips.service.impl.GameServiceImpl;
import com.fishandchips.service.impl.OwnGameServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet extends ViewBaseServlet {
    CartItemService cartItemService = new CartItemServiceImpl();
    OwnGameService ownGameService = new OwnGameServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("loggedId");

        if (req.getParameter("addToCart") != null && req.getParameter("addToCart").equals("1")) {
            /* 添加逻辑 */
            //获取游戏的id
            Integer gameId = Integer.valueOf(req.getParameter("gameId"));
            //检查游戏是否在已拥有中或者购物车中
            CartItem cartItem = cartItemService.getCartItemByGameIdAndUserId(gameId, userId);
            OwnGame ownGame = ownGameService.getOwnGameByUserIdAndGameId(userId, gameId);

            //根据查询结果，设置提示信息
            if(ownGame!=null){
                //已拥有该游戏
                req.setAttribute("addToCartResult","1");
            }else if(cartItem!=null){
                //已在购物车中
                req.setAttribute("addToCartResult","2");
            }else {
                //添加成功
                req.setAttribute("addToCartResult","3");
                cartItemService.addCartItem(new CartItem(null, gameId, userId));
            }
            req.setAttribute("buySingleResult","0");
            super.processTemplate("WEB-INF/pages/game_detail", req, resp);
        } else if (req.getParameter("deleteFromCart") != null && req.getParameter("deleteFromCart").equals("1")) {
            /* 删除逻辑 */
            Integer cartId = Integer.valueOf(req.getParameter("cartId"));
            cartItemService.deleteCartItem(new CartItem(cartId, null, null));
            Cart cart = cartItemService.getCart(new User(userId, null, null, null, null));

            session.setAttribute("cart", cart);
            super.processTemplate("WEB-INF/pages/cart/cart", req, resp);
        } else {
            /* 获取所有的购物车内容逻辑 */
            Cart cart = cartItemService.getCart(new User(userId, null, null, null, null));

            session.setAttribute("cart", cart);
            super.processTemplate("WEB-INF/pages/cart/cart", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
