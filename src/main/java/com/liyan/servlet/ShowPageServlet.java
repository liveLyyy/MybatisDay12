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
            String pagesizestr = req.getParameter("pagesize");
            String pagenumberstr = req.getParameter("pagenumber");
            int pagesize = 2;
            int pagenumber = 1;
            if (pagesizestr != null && !pagesizestr.equals("")) {
                pagesize = Integer.parseInt(req.getParameter(pagesizestr));
            }
            if (pagenumberstr != null && !pagenumberstr.equals("")) {
                pagenumber = Integer.parseInt(req.getParameter(pagenumberstr));
            }
            PageInfo pageInfo = peopleService.findPage(pagesize, pagenumber);
            req.setAttribute("pageInfo", pageInfo);
            req.getRequestDispatcher("showpage.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
