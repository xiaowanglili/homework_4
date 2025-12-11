package com.example.homework_4.controller;

import com.example.homework_4.util.CaptchaUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String captcha = CaptchaUtil.generateText();
        req.getSession().setAttribute("captcha", captcha);

        resp.setContentType("image/png");
        BufferedImage captchaImage = CaptchaUtil.generateImage(captcha);
        ImageIO.write(captchaImage, "png", resp.getOutputStream());
    }
}