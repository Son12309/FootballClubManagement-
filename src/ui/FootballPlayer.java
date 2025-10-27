/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btl.csdl;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lamtr
 */
public class FootballPlayer implements Serializable{
    private String id, name, position;
    private Date birth;
    private long goal, match;
    private double efficiency;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Date getBirth() {
        return birth;
    }

    public long getGoal() {
        return goal;
    }

    public long getMatch() {
        return match;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setGoal(long goal) {
        this.goal = goal;
    }

    public void setMatch(long match) {
        this.match = match;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    void setID(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
