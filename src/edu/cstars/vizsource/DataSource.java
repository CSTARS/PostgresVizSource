package edu.cstars.vizsource;

import javax.servlet.http.HttpServletRequest;

import com.google.visualization.datasource.Capabilities;
import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.query.Query;
import com.google.visualization.datasource.util.SqlDataSourceHelper;
import com.google.visualization.datasource.util.SqlDatabaseDescription;

@SuppressWarnings("serial")
public class DataSource extends DataSourceServlet {
	
	  /**
	   * The SQL predefined capabilities set is a special custom set for SQL
	   * databases. This implements most of the data source capabilities more 
	   * efficiently.
	   */
	  @Override
	  public Capabilities getCapabilities() {
	    return Capabilities.SQL;
	  }

	  public DataTable generateDataTable(Query query, HttpServletRequest request) throws DataSourceException {
		  String view  = request.getParameter("view");
		  if( view == null ) view = "";
		  view = view.replaceAll("\\W", ""); // clean
		  
		  try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException cnfe) {
				if( true ) System.out.println("Error in DataBaseconnection(): "+cnfe.getMessage());
			}
			DataTable dt= null;
			try {
				ServerProperties properties = new ServerProperties();
			    SqlDatabaseDescription dbDescription = new SqlDatabaseDescription(
			        properties.DB_URL,
			        properties.DB_USERNAME,
			        properties.DB_PASSWORD,
			        properties.DB_SCHEMA+"."+view);
			    
			    dt = SqlDataSourceHelper.executeQuery(query, dbDescription);
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
	    return dt;
	  }

	  /**
	   * NOTE: By default, this function returns true, which means that cross
	   * domain requests are rejected.
	   * This check is disabled here so examples can be used directly from the
	   * address bar of the browser. Bear in mind that this exposes your
	   * data source to xsrf attacks.
	   * If the only use of the data source url is from your application,
	   * that runs on the same domain, it is better to remain in restricted mode.
	   */
	  @Override
	  protected boolean isRestrictedAccessMode() {
	    return false;
	  }

}
