package org.example.domain;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student_card implements Serializable {


    private int id;

    private String fio;

    private String group_num;

    private boolean is_headman;

    private String dir_num;

    private int age;

    public Student_card(){
            super();
        }



    @JsonCreator
    public Student_card(@JsonProperty("id") int id, @JsonProperty("fio") String fio, @JsonProperty("group_num") String group_num, @JsonProperty("is_headman") boolean is_headman, @JsonProperty("dir_num") String dir_num, @JsonProperty("age") int age) {
        this.id = id;
        this.fio = fio;
        this.group_num = group_num;
        this.is_headman = is_headman;
        this.dir_num = dir_num;
        this.age = age;
    }

    void displayInfo(){
        System.out.printf("ID: %s \tfio: %s \tGroup: %s \tHeadman: %s \tDirection Number: %s\tAge: %s \n", id, fio, group_num, is_headman, dir_num, age);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getGroup_num() {
        return group_num;
    }

    public void setGroup_num(String group_num) {
        this.group_num = group_num;
    }

    public boolean isIs_headman() {
        return is_headman;
    }

    public void setIs_headman(boolean is_headman) {
        this.is_headman = is_headman;
    }

    public String getDir_num() {
        return dir_num;
    }

    public void setDir_num(String dir_num) {
        this.dir_num = dir_num;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}