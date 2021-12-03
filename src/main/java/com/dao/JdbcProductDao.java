package com.dao;

import com.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class JdbcProductDao implements DaoResource<Product> {
    private static final Logger logger = LoggerFactory.getLogger(JdbcProductDao.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> selectOne(int id) {
        if (id <= 0) {
            logger.info("Inserted incorrect id.");
            throw new RuntimeException("Inserted incorrect id.");
        }
        return jdbcTemplate.query("SELECT name, price, description FROM products WHERE id=?"
                , new BeanPropertyRowMapper<>(Product.class), new Object[]{id}).stream().findAny();
        /*Product product = new Product();
        try(PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT name, price, description FROM products WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            product.setId(id);
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setDescription(resultSet.getString("description"));
            resultSet.close();
        } catch (SQLException e) {
            logger.info("Couldn`t select product {} from db" + e, id);
            throw new RuntimeException("Couldn`t select product " + id + " from db", e);
        }
        logger.info("The product with id: {} selected", id);
        return product;*/
    }

    @Override
    public Optional<Product> getByName(String name) {
        if (name == null) {
            logger.info("Inserted incorrect name.");
            throw new RuntimeException("Inserted incorrect name.");
        }
        return jdbcTemplate.query("SELECT name, price, description FROM products WHERE name=?"
                , new BeanPropertyRowMapper<>(Product.class), new Object[]{name}).stream().findAny();

        /*Product product = new Product();
        try(PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT id, name, price, description FROM products WHERE name=?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setDescription(resultSet.getString("description"));
            resultSet.close();
        } catch (SQLException e) {
            logger.info("Couldn`t select product {} from db" + e, id);
            throw new RuntimeException("Couldn`t select product " + id + " from db", e);
        }
        logger.info("The product with id: {} selected", id);
        return Optional.of(product);*/
    }


    @Override
    public List<Product> selectAll() {
        return jdbcTemplate.query("SELECT id, name, price, description FROM products", new BeanPropertyRowMapper<>(Product.class));

        /*List<Product> products = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id, name, price, description FROM products");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                String desc = resultSet.getString("description");
                product.setDescription(desc == null ? "" : desc);
                products.add(product);
            }
        } catch (SQLException e) {
            logger.info("Couldn`t select from db" + e);
            throw new RuntimeException("Couldn`t select from db", e);
        }
        logger.info("All products selected.");
        return products;*/
    }

    @Override
    public void create(Product product) {
        jdbcTemplate.update("INSERT INTO products (name, price, description) VALUES (?, ?, ?)",
                product.getName(), product.getPrice(), product.getDescription());
        logger.info("The product {} created.", product);

        /*try(PreparedStatement statement = dataSource.getConnection().prepareStatement("INSERT INTO products (name, price, description) VALUES (?, ?, ?)")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t create new Product." + e);
            throw new RuntimeException("Couldn`t create new Product.", e);
        }*/
    }

    @Override
    public void updateOne(Product product) {
        jdbcTemplate.update("UPDATE products SET name = ?, price = ?, description = ? WHERE name = ?",
                product.getName(), product.getPrice(), product.getDescription(), product.getName());
        logger.info("The product: {} updated", product);

        /*try(PreparedStatement statement = dataSource.getConnection().prepareStatement("UPDATE products SET name = ?, price = ?, description = ? WHERE id = ?")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t update by id: {} - {}" + e, id, product);
            throw new RuntimeException("Couldn`t update by id: " + id + ", " + product, e);
        }*/
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
        logger.info("The product with id: {} deleted", id);
        /*try(PreparedStatement statement = dataSource.getConnection().prepareStatement("DELETE FROM products WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Couldn`t delete product by id: {}" + e, id);
            throw new RuntimeException("Couldn`t delete product by id: " + id, e);
        }
        logger.info("The product with id: {} deleted", id);*/
    }
}
