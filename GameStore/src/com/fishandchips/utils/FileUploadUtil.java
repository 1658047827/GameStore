package com.fishandchips.utils;

import com.fishandchips.pojo.Game;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileUploadUtil {
    /**
     * 上传图片文件方法
     *
     * @param request httpServlet请求
     * @return java.lang.String
     */
    public static Game process(HttpServletRequest request) throws ServletException, IOException {
        // 准备上传
/*        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("GBK");*/

        // 请求信息中的内容是否是multipart类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // 获取到服务器项目的根目录下的uploads目录
        String uploadFilePath = request.getServletContext().getRealPath("/static/uploads");
        //返回的文件名列表
        Game game = new Game();
        //判断请求信息中的内容 是否是“multipart/form-data”类型
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            //设置上传文件大小：单位 byte
            upload.setSizeMax(1024 * 1024 * 30); //最大30M的图片
            try {
                // 解析form表单中所有文件
                List<FileItem> items = upload.parseRequest(request);

                game.setGameName(items.get(0).getString("UTF-8"));

                String fileName = items.get(1).getName();
                if (fileName != null && !"".equals(fileName)) {
                    String s = fileName.substring(fileName.lastIndexOf('.') + 1);
                    File saveFile = new File(uploadFilePath + "/" + fileName);
                    //上传
                    items.get(1).write(saveFile);
                    // 文件名
                    game.setGameImg(fileName);
                }

                game.setCompany(items.get(2).getString("UTF-8"));
                double gamePrice = Double.parseDouble(items.get(3).getString("UTF-8"));
                game.setPrice(gamePrice);
                game.setGameType(items.get(4).getString("UTF-8"));

                fileName = items.get(5).getName();
                if (fileName != null && !"".equals(fileName)) {
                    String s = fileName.substring(fileName.lastIndexOf('.') + 1);
                    File saveFile = new File(uploadFilePath + "/" + fileName);
                    //上传
                    items.get(5).write(saveFile);
                    // 文件名
                    game.setDetailGameImg(fileName);
                }

                game.setDescription(items.get(6).getString("UTF-8"));
                game.setSaleCount(0);

            } catch (Exception e) {
                e.printStackTrace();
                //图片过大提示

            }
        }
        return game;
    }
}
