/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btl.csdl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lamtr
 */
public class DAO {
    private String url = "jdbc:mysql://localhost:3306/Football?autoReconnect=true&useSSL=false";
    private String username = "sqlusername";
    private String password = "sqlpassword";
    private Connection conn;
    
    public DAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean addPlayer(FootballPlayer s){
        String sql = "INSERT INTO Football(ID, name, position, birth, goal, match, efficiency)" + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getPosition());
            ps.setDate(4, new Date(s.getBirth().getTime()));
            ps.setLong(5, s.getGoal());
            ps.setLong(6, s.getMatch());
            ps.setDouble(7, s.getEfficiency());
            
            return ps.executeUpdate() > 0;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updatePlayer(FootballPlayer s) {
        String sql = "UPDATE player SET id = ?, name = ?, position = ?, birth = ?, goal = ?, match = ?, efficiency = ? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getPosition());
            ps.setDate(4, new Date(s.getBirth().getTime()));
            ps.setLong(5, s.getGoal());
            ps.setLong(6, s.getMatch());
            ps.setDouble(7, s.getEfficiency());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deletePlayer(String id) {
        String sql = "DELETE FROM Football WHERE ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<FootballPlayer> getListFootballPlayer() {
        ArrayList<FootballPlayer> list = new ArrayList<>();
        String sql = "SELECT * FROM Football";
    
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FootballPlayer s = new FootballPlayer();
                s.setID(rs.getString("ID"));
                s.setName(rs.getString("FullName"));
                s.setPosition(rs.getString("Position"));
                s.setBirth(rs.getDate("Birth"));
                s.setGoal(rs.getLong("Goal"));
                s.setMatch(rs.getLong("Match"));
                s.setEfficiency(rs.getDouble("Efficiency"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return list;
    }
}
