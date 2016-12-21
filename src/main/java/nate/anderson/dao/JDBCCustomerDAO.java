package nate.anderson.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import nate.anderson.model.Customer;

@Component
public class JDBCCustomerDAO implements CustomerDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCCustomerDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		String sqlQuery = "SELECT * " + 
						  "FROM customers";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery);
		
		return mapResultsToCustomerList(results);
	}

	private List<Customer> mapResultsToCustomerList(SqlRowSet results) {
		List<Customer> allCustomers = new ArrayList<Customer>();
		
		while (results.next()) {
			Customer customer = new Customer();
			customer.setCustomerId(results.getInt("customer_id"));
			customer.setCompany(results.getString("company"));
			customer.setAddress(results.getString("address"));
			customer.setCity(results.getString("city"));
			customer.setState(results.getString("state"));
			customer.setZip(results.getInt("zip"));
			customer.setCreatedAt(results.getDate("created_at").toLocalDate());
			customer.setUpdatedAt(results.getDate("updated_at").toLocalDate());
			allCustomers.add(customer);
		}
		
		return allCustomers;
		
	}
	
}
