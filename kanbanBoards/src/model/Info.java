package model;

public class Info {
    private int id;
    private String content;
    public Info(int id, String content){
        this.id = id;
        this.content = content;
    }

    public int getId(){ return id;}
    public String getContent() {return content;}
}
