package DAO.Entities;

import java.util.Date;

/**
 *  This is the entity class of task.
 * Contains all Task data.
 * @author Viktor Bolshakov on 30.01.16.
 */
public class Task {
    private int id;
    private String title;
    private Enum level;
    private String description;
    private int quanPlan;
    private int quanFact;
    private int weight;
    private Date start;
    private Date end;
    private double performance;
    private double bonus;
    private int implementer;
    private double ratio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Enum getLevel() {
        return level;
    }

    public void setLevel(Enum level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuanPlan() {
        return quanPlan;
    }

    public void setQuanPlan(int quanPlan) {
        this.quanPlan = quanPlan;
    }

    public int getQuanFact() {
        return quanFact;
    }

    public void setQuanFact(int quanFact) {
        this.quanFact = quanFact;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public int getImplementer() {
        return implementer;
    }

    public void setImplementer(int implementer) {
        this.implementer = implementer;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
