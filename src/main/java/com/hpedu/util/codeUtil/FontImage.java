package com.hpedu.util.codeUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class FontImage {
	 // 默认格式  
    private static final String FORMAT_NAME = "JPG";  
    // 默认 宽度  
    private static final int WIDTH = 152;  
    // 默认 高度  
     private static final int HEIGHT =42;  
            
     /** 
      * 创建图片 
      * @param content 内容 
      * @param font  字体 
      * @param width 宽 
      * @param height 高 
      * @return 
      */  
    private static BufferedImage createImage(String content,Integer width,Integer height){    
           BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);     
           Graphics2D g2 = (Graphics2D)bi.getGraphics();     
           g2.setBackground(new Color(210, 239, 233)); //设置背景颜色    
           g2.clearRect(0, 0, width, height);     
           g2.setPaint(new Color(168, 231, 208)); //设置字体颜色    
           g2.setColor(new Color(111, 184, 158));//设置边框颜色
           RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width-2, height-2, 10, 10);
           g2.draw(roundedRectangle); 
           int fx=8;
           int fy=(int)(height*0.7);
   		for (int i = 0; i < content.length(); i++) {
   			g2.setFont(new Font("Arial",Font.PLAIN,fy));
   			g2.drawString(String.valueOf(content.charAt(i)), fx, fy);
   			fx+=17;
   		}   
             
           return bi;  
    }  
  
    /** 
     *  获取图片 
     * @param content 内容 
     * @param font 字体 
     * @param width 宽 
     * @param height 高 
     * @param destPath 输出路径 
     * @throws IOException  
     */  
    public static String getImage(String content,Integer width,Integer height,String destPath) throws IOException{  
        mkdirs(destPath);  
        String filename = UUID.randomUUID().toString()+".jpg";  
        ImageIO.write(createImage(content,width,height),FORMAT_NAME, new File(destPath+"/"+filename));
        return filename;
    }  
    /** 
     *  获取图片 
     * @param content 内容 
     * @param font 字体 
     * @param width 宽 
     * @param height 高 
     * @param destPath 输出路径 
     * @throws IOException  
     */  
    public static String getImage(String content,String destPath) throws IOException{  
    	return getImage(content,WIDTH,HEIGHT,destPath);  
    }  
    /** 
     * 获取图片 
     * @param content 内容 
     * @param font 字体 
     * @param width 宽 
     * @param height 高 
     * @param output 输出流 
     * @throws IOException 
     */  
    public static void getImage(String content,Integer width,Integer height, OutputStream output) throws IOException{  
        ImageIO.write(createImage(content,width,height), FORMAT_NAME, output);    
    }  
      
    
 
      /** 
       * 创建 目录 
       * @param destPath 
       */  
      public static void mkdirs(String destPath) {  
           File file =new File(destPath);     
           //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)  
           if (!file.exists() && !file.isDirectory()) {  
               file.mkdirs();  
           }  
       }  
      
    public static void main(String[] args) throws Exception {  
       System.out.println(getImage(BaseUtil.random(8), "d:/test"));  
 
   }  

}
