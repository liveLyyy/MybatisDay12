package com.liyan.servlet;

import com.liyan.file.PageInfo;
import com.liyan.service.Impl.PeopleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page")
public class ShowPageServlet extends HttpServlet {
    private PeopleServiceImpl peopleService = new PeopleServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pageSizestr = req.getParameter("pageSize");
            String pageNumberstr = req.getParameter("pageNumber");
            int pageSize = 2;
            int pageNumber = 1;
            if (pageSizestr != null && !pageSizestr.equals("")) {
                pageSize = Integer.parseInt(pageSizestr);
            }
            if (pageNumberstr != null && !pageNumberstr.equals("")) {
                pageNumber = Integer.parseInt(pageNumberstr);
            }
            PageInfo pageInfo = peopleService.findPage(pageSize, pageNumber);
            req.setAttribute("pageInfo", pageInfo);
            req.getRequestDispatcher("showpage.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
