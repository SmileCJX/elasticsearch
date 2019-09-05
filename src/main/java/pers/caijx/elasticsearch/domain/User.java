package pers.caijx.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by caijx on 2018/7/1/001.
 */
public class User {

    private String name;

    @JsonIgnore   //不显示给前端
    private String password;

    private Integer age;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")  //格式化
    private Date birthday;

    @JsonInclude(JsonInclude.Include.NON_NULL)  //当为空时是不会显示给前端的
    private String desc;

    public User() {
    }

    public User(String name, String password, Integer age, Date birthday, String desc) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.birthday = birthday;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "desc='" + desc + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
