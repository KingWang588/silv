package com.yhy.hzzll.entity;

/**
 * 债权易物
 * 
 * @author 一合鱼
 *
 */
public class MyPAskLawyerEntity {
	
    /**
     * id : 2
     * lawyernumber : 1005号苏律师
     * lawyername : 苏文进
     * firstcalltimes : 0
     * secondcalltimes : 9
     */

    private String id;
    private String lawyernumber;
    private String lawyername;
    private String firstcalltimes;
    private String secondcalltimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLawyernumber() {
        return lawyernumber;
    }

    public void setLawyernumber(String lawyernumber) {
        this.lawyernumber = lawyernumber;
    }

    public String getLawyername() {
        return lawyername;
    }

    public void setLawyername(String lawyername) {
        this.lawyername = lawyername;
    }

    public String getFirstcalltimes() {
        return firstcalltimes;
    }

    public void setFirstcalltimes(String firstcalltimes) {
        this.firstcalltimes = firstcalltimes;
    }

    public String getSecondcalltimes() {
        return secondcalltimes;
    }

    public void setSecondcalltimes(String secondcalltimes) {
        this.secondcalltimes = secondcalltimes;
    }

}
