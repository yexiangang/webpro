package cn.oa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import cn.oa.model.Product;
import cn.oa.utils.JdbcUtils;

public class ProductDaoImpl {

	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		Product product = daoImpl.getById(1);
		System.out.println(product);
		daoImpl.delete(3);
	}

	public List<Product> queryByName(String name) {
		String sql = "select * from product where name like ?";
		List<Product> proList = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// 可以生成预编译的SQL语句,在多条记录插入的时候效率高,而且解决了SQL注入问题.
			prep = conn.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			rs = prep.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setDate(rs.getDate("date"));
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setRemark(rs.getString("remark"));
				product.setPrice(rs.getDouble("price"));
				proList.add(product);
			}
			return proList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, prep, conn);
		}
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		Product product = null;
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// 可以生成预编译的SQL语句,在多条记录插入的时候效率高,而且解决了SQL注入问题.
			prep = conn.prepareStatement(sql);
			prep.setInt(1, id);
			rs = prep.executeQuery();
			while (rs.next()) {
				product = new Product();
				product.setDate(rs.getDate("date"));
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setRemark(rs.getString("remark"));
				product.setPrice(rs.getDouble("price"));
			}
			return product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, prep, conn);
		}
	}

	public int update(Product product) {
		String sql = "update product set name=?,price=?,remark=? where id = ?";
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// 可以生成预编译的SQL语句,在多条记录插入的时候效率高,而且解决了SQL注入问题.
			prep = conn.prepareStatement(sql);
			prep.setString(1, product.getName());
			prep.setDouble(2, product.getPrice());
			prep.setString(3, product.getRemark());
			prep.setInt(4, product.getId());
			return prep.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, prep, conn);
		}
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// 可以生成预编译的SQL语句,在多条记录插入的时候效率高,而且解决了SQL注入问题.
			prep = conn.prepareStatement(sql);
			prep.setInt(1, id);
			return prep.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, prep, conn);
		}
	}

	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?);";
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// 可以生成预编译的SQL语句,在多条记录插入的时候效率高,而且解决了SQL注入问题.
			prep = conn.prepareStatement(sql);
			prep.setString(1, product.getName());
			prep.setDouble(2, product.getPrice());
			prep.setString(3, product.getRemark());
			return prep.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, prep, conn);
		}
	}

}
