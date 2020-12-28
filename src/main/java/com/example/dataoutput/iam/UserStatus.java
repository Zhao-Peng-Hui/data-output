package com.example.dataoutput.iam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jayx on 2018/8/22.
 */
public class UserStatus {

    public static String leave = "41";

    public static Map<String,String> STATUS = new HashMap<>();

    static{
        STATUS.put("11","在册在岗");
        STATUS.put("12","在册在岗-留用察看");
        STATUS.put("20","在册不在岗-工伤/职业病");
        STATUS.put("21","在册不在岗-出国援外人员");
        STATUS.put("22","在册不在岗-出国劳务人员");
        STATUS.put("23","在册不在岗-长期学习人员(六个月以上)");
        STATUS.put("24","在册不在岗-长期休假人员(六个月以上)");
        STATUS.put("25","在册不在岗-内部退养人员");
        STATUS.put("26","在册不在岗-下岗人员");
        STATUS.put("27","在册不在岗-待岗人员");
        STATUS.put("28","在册不在岗-外借外派人员(企事业单位间)");
        STATUS.put("29","在册不在岗-其他");
        STATUS.put("2A","在册不在岗-国内劳务人员");
        STATUS.put("2B","在册不在岗-外借外派人员(企事业单位内)");
        STATUS.put("2C","在册不在岗-退出领导岗位人员");
        STATUS.put("2D","在册不在岗-停薪留职");
        STATUS.put("31","不在册在岗-借入人员(企事业单位间)");
        STATUS.put("32","不在册在岗-劳务输出人员");
        STATUS.put("33","不在册在岗-借入人员(企事业单位内)");
        STATUS.put("34","不在册在岗-返聘人员");
        STATUS.put("39","不在册在岗-其他人员");
        STATUS.put("41","不在册不在岗");
        STATUS.put("1A","在册在岗-转岗调动人员");
        STATUS.put("1B","在册在岗-转岗内部劳务人员");
        STATUS.put("1C","在册在岗-企业间劳务输出人员");
        STATUS.put("1D","在册在岗-承包剥离的企业办社会业务人员");
        STATUS.put("1E","在册在岗-输出劳务到合作单位人员");
        STATUS.put("1F","在册在岗-其他外部输出劳务人员");
        STATUS.put("2E","在册不在岗-离岗歇业人员");
        STATUS.put("2H","在册不在岗-借聘输出");
        STATUS.put("2I","在册不在岗-对口支持输出");
        STATUS.put("2J","在册不在岗-休育儿陪护假人员");
        STATUS.put("2K","在册不在岗-休哺乳假人员");
        STATUS.put("2L","在册不在岗-息工放假人员");
        STATUS.put("36","不在册在岗-借聘输入");
        STATUS.put("37","不在册在岗-对口支持输入");
        STATUS.put("13","在册在岗-退出领导岗位人员");
        STATUS.put("14","在册在岗-享受职级待遇");
        STATUS.put("15","在册在岗-对口支持外派");
        STATUS.put("3A","不在册在岗-非全日制人员");
        STATUS.put("3B","不在册在岗-博士后进站人员");
        STATUS.put("3C","不在册在岗-独立董监事");
        STATUS.put("3D","不在册在岗-合作方派出人员");
        STATUS.put("42","不在册不在岗-劳动合同中止");
        STATUS.put("2M","在册不在岗-短期离岗");
        STATUS.put("2N","在册不在岗-阶段性调出");
        STATUS.put("3N","不在册在岗-阶段性调入");

    }

    public static void main(String[] args) {
        System.out.println(UserStatus.STATUS);
    }
}
