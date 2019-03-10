package com.store.servlet;

import com.store.domain.Category;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;
import com.store.utils.MD5Utils;
import com.store.utils.UUIDUtils;
import com.store.utils.UploadUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddProductServlet",urlPatterns = "/addProduct")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String,Object> map = new HashMap<>();

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item: list) {
                if (item.isFormField()) {
                    map.put(item.getFieldName(),item.getString("utf-8"));
                } else {
                    String fileName = item.getFieldName();
                    String realName = UploadUtils.getRealName(fileName);

                    String path = this.getServletContext().getRealPath("/products/1");
                    InputStream inputStream = item.getInputStream();
                    FileOutputStream outputStream = new FileOutputStream(new File(path,realName));

                    IOUtils.copy(inputStream,outputStream);
                    inputStream.close();
                    outputStream.close();
                    item.delete();


                    map.put(fileName,"/products/1" + realName);
                }
            }
            Product p = new Product();
            p.setPid(UUIDUtils.getId());
            p.setPdate(new Date());
            Category category = new Category();
            category.setCid((String) map.get("cid"));
            p.setCategory(category);
            BeanUtils.populate(p,map);

            ProductService productService = (ProductService) BeanFactory.getBean("ProductService");
            productService.add(p);
            response.sendRedirect(request.getContextPath() + "/adminProduct?method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
