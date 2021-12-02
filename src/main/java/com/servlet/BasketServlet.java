package servlet;

import com.configuration.ProductServiceFactory;
import com.model.Product;
import com.service.BasketService;
import com.service.DefaultService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BasketServlet extends HttpServlet {
    private final DefaultService productService = ProductServiceFactory.getInstance();
    private BasketService basketService = new BasketService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Map<String, Object> pageVariables = new HashMap<>();
        String url = req.getRequestURI();
        String name = getNameFromPath(url);
        Product product = (Product) productService.getByName(name).get();
        basketService.addProduct(product);
        //pageVariables.put("product", product);

        resp.setContentType("text/html;charset=utf-8");
        //String page = PageGenerator.instance().getPage("edit.ftl", pageVariables);
        //resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private String getNameFromPath(String url) {
        String name = url.substring(url.lastIndexOf('/') + 1);
        return name;
    }
}
