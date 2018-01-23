package com.upload;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "UploadServlet",urlPatterns = "/upload")
@MultipartConfig  //form表单中使用enctype="multipart/form-data"这个属性，读取值需要添加这个注解
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        System.out.println(username);


        Part part = request.getPart("f");
        System.out.println(part);
        String header = part.getHeader("content-disposition");
        System.out.println(header);

        String fileName = header.substring(header.indexOf("filename=")+10,header.length()-1);
        System.out.println(fileName);

        String uuidName = UploadUtils.getUUIDName(fileName);

        String dir = UploadUtils.getDir(uuidName);
        String realPath = this.getServletContext().getRealPath("/uploadFiles" + dir);
        System.out.println(realPath);

        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }

        InputStream in = part.getInputStream();
        FileOutputStream out = new FileOutputStream(new File(realPath,uuidName));

        IOUtils.copy(in,out);
        out.close();
        in.close();

        part.delete();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
