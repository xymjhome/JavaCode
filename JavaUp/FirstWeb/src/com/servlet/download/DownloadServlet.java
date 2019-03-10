package com.servlet.download;



import com.servlet.utils.DownLoadUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "DownloadServlet",urlPatterns = "/download")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取文件名
        System.out.println("55555555555");
        String filename = request.getParameter("filename");

        //注意中文乱码问题,这个地方是解决请求时的乱码问题
        filename = new String(filename.getBytes("iso8859-1"),"utf-8");



        System.out.println("filename:"+filename);
        //根据文件名设置头中文件类型
            //获取文件类型
        ServletContext servletContext = getServletContext();
        String mimeType = servletContext.getMimeType(filename);
        System.out.println(mimeType);
            //设置头文件content-type
        response.setContentType(mimeType);

        //设置下载头信息 content-disposition

        //这个地方解决下载名处中文乱码问题，针对常见浏览器
        //方式1:通过的方式 通过工具类编码
        String name = DownLoadUtils.getName(request.getHeader("user-agent"), filename);
        response.setHeader("content-disposition","attachment;filename="+name);

        //方式2:网络上的方式 (8成好使)
        //response.setHeader("content-disposition", "attachment;filename="+new String(filename.getBytes("gbk"),"iso8859-1"));


        //获取服务器文件读入流
        System.out.println(servletContext.getRealPath(filename));
        InputStream resourceAsStream = servletContext.getResourceAsStream("/download/" + filename);
        System.out.println(resourceAsStream);
        //获取输出到浏览器下载的输出流
        ServletOutputStream outputStream = response.getOutputStream();

        //方式一：按照原始流操作流程
//        int len = 0;
//        byte[] arr = new byte[1024];
//        while((len = resourceAsStream.read(arr)) != -1){
//            outputStream.write(arr);
//        }
//        resourceAsStream.close();
//        outputStream.close();
        //方式二：利用commons-io-1.4外部jar
        IOUtils.copy(resourceAsStream,outputStream);
        resourceAsStream.close();
        outputStream.close();
    }
}
