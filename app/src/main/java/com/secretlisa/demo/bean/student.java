package com.secretlisa.demo.bean;

/**
 * Created by hehaitao on 2015/6/1.
 */
public class student {
    private String  name;
    private String ico;
    private  String intro;

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", ico='" + ico + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
