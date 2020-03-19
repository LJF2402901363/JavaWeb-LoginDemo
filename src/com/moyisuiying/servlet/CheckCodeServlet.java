package com.moyisuiying.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-01-23 11:54
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码的大小
        int weiht = 80;
        int hight = 30;
        //获取图片缓冲流
        BufferedImage bufferedImage = new BufferedImage(weiht,hight,BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics graphics = bufferedImage.getGraphics();
        //设置画笔颜色
        graphics.setColor(Color.PINK);
        //填充矩形
        graphics.fillRect(0,0,weiht,hight);
        graphics.setFont(new Font("微软雅黑",Font.BOLD,14));
        graphics.setColor(Color.BLUE);
        //绘画边框
        graphics.drawRect(0,0,weiht-1,hight-1);
        //绘画验证码
        String code = this.getCode();
        request.getSession().setAttribute("checkcode",code);
        Color color = null;
        Random random = new Random();
        graphics.setFont(new Font("微软雅黑",Font.BOLD,20));
        for (int i = 1;i <= code.length();i++){
            //随机生成颜色
            color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            graphics.setColor(color);
            //逐个画验证码
            graphics.drawString(code.charAt(i-1)+"",weiht/5*i-5,hight/2+5);
        }
        //绘画干扰元素
        int x1 = weiht;
        int y1 = hight;
        int x2 = weiht;
        int y2 = hight;
        graphics.setFont(new Font("微软雅黑",Font.BOLD,14));
        for (int i = 0;i < 8;i++){
            //随机生成颜色
            color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            graphics.setColor(color);
            //逐个画验证码
           graphics.drawLine(random.nextInt(x1),random.nextInt(y1),random.nextInt(x2),random.nextInt(y2));
        }
        //将内存中的图片缓冲流写入到网页中
        ImageIO.write(bufferedImage,"png",response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    /**
     * @Description :获取随机验证码
     * @Date 12:07 2020/1/23 0023
     * @Param * @param  ：
     * @return java.lang.String
     **/
    private String getCode(){
        String s = "ABCDEFGHIJKLMNOPQRSTUVWSYZabcdefghijklmnopqrstuvwsyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int nextInt = 0;
        for (int i = 0; i < 4;i++){
            nextInt = random.nextInt(s.length());
            sb.append( s.charAt(nextInt));
        }
      return sb.toString();
    }
}
