package com.hpedu.web.core.wxpay.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class Qrcode {
    public void createQRCoder(String content, HttpServletResponse response) {
        try {
            int width = 400;//二维码宽度
            int height = 400;//二维码高度
            String qrcodeFormat = "png";//图片类型
            HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //MultiFormatWriter 对象为生成二维码的核心类，后面的 MatrixToImageWriter 只是将二维码矩阵输出到图片上面。
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}
