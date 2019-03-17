package model;

public class Project {
    private int id;
    private String projectName;
    private int poId;
    private String startDate;
    private String estimateEndDate;
    private int day;
    private int remainDay;
    private String joinMember;
    public Project(int id, String projectName, int poId, String startDate, String estimateEndDate, int day, int remainDay, String joinMember){
        this.id = id;
        this.projectName = projectName;
        this.poId = poId;
        this.startDate = startDate;
        this.estimateEndDate = estimateEndDate;
        this.day = day;
        this.remainDay = remainDay;
        this.joinMember = joinMember;
    }

    public int getId(){return id;}
    public String getProjectName()
    {
        if(id == 1)
        {
            return "<a href='#'>" + projectName + "</a>";
        }
        else
        {
            return "<a href='#'>" + projectName + "</a>";
        }
    }
    public int getNumberOfMember(){
        String[] tokens = joinMember.split(",");
        return tokens.length;
    }
    public int getProjectRemainDay(){return remainDay;}
}
