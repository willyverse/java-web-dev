package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Project> selectList() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT PNO, PNAME, STA_DATE, END_DATE, STATE"
									+ " FROM PROJECTS" + " ORDER BY PNO DESC");
			ArrayList<Project> projects = new ArrayList<Project>();
			
			while (rs.next()) {
				projects.add(new Project().setNo(rs.getInt("PNO"))
						.setTitle(rs.getString("PNAME"))
						.setStartDate(rs.getDate("STA_DATE"))
						.setEndDate(rs.getDate("END_DATE"))
						.setState(rs.getInt("STATE")));
			}
			return projects;
			
		}	catch (Exception e) {
			throw e;
	    } finally {
	        try {if (rs != null) rs.close();} catch(Exception e) {}
	        try {if (stmt != null) stmt.close();} catch(Exception e) {}
	        try {if (conn != null) conn.close();} catch(Exception e) {}
	    }
	}

	@Override
	public int insert(Project project) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = ds.getConnection();
		stmt = conn.prepareStatement("INSERT INTO PROJECTS("
				+ "PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE, TAGS)"
				+ "VALUES (?, ?, ?, ?, 0, NOW(), ?)");
		stmt.setString(1, project.getTitle());
		stmt.setString(2, project.getContent());
		stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
		stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
		stmt.setString(5, project.getTags());
		return stmt.executeUpdate();
	}

	@Override
	public Project selectOne(int no) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT PNO, PNAME, CONTENT,"
					+ "STA_DATE, END_DATE, STATE, CRE_DATE, TAGS"
					+ " FROM PROJECTS"
					+ " WHERE PNO = " + no);
			if (rs.next()) {
				return new Project()
				.setNo(rs.getInt("PNO"))
				.setTitle(rs.getString("PNAME"))
				.setContent(rs.getString("CONTENT"))
				.setStartDate(rs.getDate("STA_DATE"))
				.setEndDate(rs.getDate("END_DATE"))
				.setState(rs.getInt("STATE"))
				.setCreatedDate(rs.getDate("CRE_DATE"))
				.setTags(rs.getString("TAGS"));
			} else {
				throw new Exception("해당 번호의 프로젝트를 찾을 수 없당");
			}
			
		} catch (Exception e) {
			throw e;
	    } finally {
	        try {if (rs != null) rs.close();} catch(Exception e) {}
	        try {if (stmt != null) stmt.close();} catch(Exception e) {}
	        try {if (conn != null) conn.close();} catch(Exception e) {}
	    }
	}

	@Override
	public int update(Project project) throws Exception {
		System.out.println("update가 실행됨 " + project);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
		conn = ds.getConnection();
		stmt = conn.prepareStatement("UPDATE PROJECTS"
				+ " SET PNAME=?, CONTENT=?, STA_DATE=?, END_DATE=?, STATE=?, TAGS=?"
				+ " WHERE PNO=?");
		stmt.setString(1, project.getTitle());
		stmt.setString(2,  project.getContent());
		stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
		stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
		stmt.setInt(5,  project.getState());
		stmt.setString(6, project.getTags());
		stmt.setInt(7, project.getNo());
		return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
	    } finally {
	        try {if (stmt != null) stmt.close();} catch(Exception e) {}
	        try {if (conn != null) conn.close();} catch(Exception e) {}
	    }
	}
}