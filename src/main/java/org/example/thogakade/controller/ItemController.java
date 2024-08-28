package org.example.thogakade.controller;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.thogakade.bo.BOFactory;
import org.example.thogakade.bo.custom.ItemBO;
import org.example.thogakade.dto.ItemDTO;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {
    private Connection connection;
    ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().BOTypes(BOFactory.BOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()){
            List<ItemDTO> itemDTOList = itemBO.getAllItem(connection);
            if (itemDTOList != null){
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(itemDTOList,resp.getWriter());
            }else {
                writer.write("Not Available");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().contains("application/json") || req.getContentType() == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

        try(var writer = resp.getWriter()){
            boolean isSaved = itemBO.saveItem(itemDTO,connection);
            if (isSaved){
                writer.write("successfully");
            }else {
                writer.write("Try Again");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemDTO itemDTO = JsonbBuilder.create().fromJson(req.getReader(), ItemDTO.class);
        try(var writer = resp.getWriter()){
            boolean isUpdated = itemBO.updateItem(itemDTO,connection);
            if (isUpdated){
                writer.write("update successfully");
            }else {
                writer.write("Please Try Again.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemDTO itemDTO = JsonbBuilder.create().fromJson(req.getReader(), ItemDTO.class);
        try(var writer = resp.getWriter()){
            var isDelete = itemBO.deleteItem(itemDTO.getItemCode(), connection);
            if (isDelete){
                writer.write("Delete Successfully");
            }else {
                writer.write("Please Try again!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        try{
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/groStorePosSystem");
            this.connection = pool.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
