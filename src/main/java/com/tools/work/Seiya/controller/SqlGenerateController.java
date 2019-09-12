package com.tools.work.Seiya.controller;

import com.tools.work.Seiya.impl.Author;
import com.tools.work.Seiya.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SqlGenerateController {
    @Autowired
    private Author author;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return author.getEmail();
    }

    @GetMapping("/generate/sql")
    public String generateSql(
            @RequestParam(name = "memberid", required = false, defaultValue = "") Long memberId,
            @RequestParam(name = "account", required = false, defaultValue = "") String account
    ) {
        try {
            if (StringUtils.isEmpty(Long.toString(memberId))) {
                return "uid lost!";
            }
            long mod = memberId % 128;
            DecimalFormat decimalFormat = new DecimalFormat("000");
            String index = decimalFormat.format(mod);
            String memberCondition = " where memberid = " + memberId + ";</br>";
            String accountConditon = " where login_account = '" + account +"';</br>";

            String baseSql = "select * from yx_member_base_" + index + memberCondition;
            String extSql = "select * from yx_member_ext_" + index + memberCondition;
            String openInfoSql = "select * from yx_member_open_info_" + index + memberCondition;

            String accountSql = null;
            if (!StringUtils.isEmpty(account)) {
                long hash = HashUtils.hash(account);
                long mod2 = Math.abs(hash % 128);
                String index2 = decimalFormat.format(mod2);
                accountSql = "select * from yx_member_account_" + index2 + accountConditon;
            }

            String sql = baseSql + extSql + openInfoSql + accountSql;
            return sql;
        } catch (Exception e) {
            return "error";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/mac/Desktop/jixuan/bind/bak5/uid")), "UTF-8"));
        BufferedWriter bw00 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/mac/Desktop/jixuan/bind/bak5/sql_00")), "UTF-8"));
        BufferedWriter bw01 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/mac/Desktop/jixuan/bind/bak5/sql_01")), "UTF-8"));
        BufferedWriter bw02 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/mac/Desktop/jixuan/bind/bak5/sql_02")), "UTF-8"));
        BufferedWriter bw03 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/mac/Desktop/jixuan/bind/bak5/sql_03")), "UTF-8"));
        BufferedWriter bw04 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/mac/Desktop/jixuan/bind/bak5/sql_04")), "UTF-8"));
        BufferedWriter bw05 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/mac/Desktop/jixuan/bind/bak5/sql_05")), "UTF-8"));
        BufferedWriter bw06 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/mac/Desktop/jixuan/bind/bak5/sql_06")), "UTF-8"));
        BufferedWriter bw07 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/mac/Desktop/jixuan/bind/bak5/sql_07")), "UTF-8"));

        String uid = null;
        DecimalFormat decimalFormat = new DecimalFormat("000");
        while ((uid = br.readLine()) != null) {
            System.out.println(uid);
            long hash = HashUtils.hash(uid);
            long mod2 = Math.abs(hash % 128);
            String accountConditon = " where login_account = '" + uid +"' and type = 20 ;";
            if (mod2 >= 0 && mod2 < 16) {
                String index2 = decimalFormat.format(mod2);
                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
                bw00.write(accountSql);
                bw00.newLine();
            }
            if (mod2 >= 16 && mod2 < 32) {
                String index2 = decimalFormat.format(mod2);
                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
                bw01.write(accountSql);
                bw01.newLine();
            }
            if (mod2 >= 32 && mod2 < 48) {
                String index2 = decimalFormat.format(mod2);
                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
                bw02.write(accountSql);
                bw02.newLine();
            }
            if (mod2 >= 48 && mod2 < 64) {
                String index2 = decimalFormat.format(mod2);
                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
                bw03.write(accountSql);
                bw03.newLine();
            }
            if (mod2 >= 64 && mod2 < 80) {
                String index2 = decimalFormat.format(mod2);
                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
                bw04.write(accountSql);
                bw04.newLine();
            }
            if (mod2 >= 80 && mod2 < 96) {
                String index2 = decimalFormat.format(mod2);
                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
                bw05.write(accountSql);
                bw05.newLine();
            }
            if (mod2 >= 96 && mod2 < 112) {
                String index2 = decimalFormat.format(mod2);
                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
                bw06.write(accountSql);
                bw06.newLine();
            }
            if (mod2 >= 112 && mod2 < 128) {
                String index2 = decimalFormat.format(mod2);
                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
                bw07.write(accountSql);
                bw07.newLine();
            }
        }
        br.close();
        bw00.close();
        bw01.close();
        bw02.close();
        bw03.close();
        bw04.close();
        bw05.close();
        bw06.close();
        bw07.close();


//        String uids = "";
//        String[] uidArr = uids.split(",");
//        DecimalFormat decimalFormat = new DecimalFormat("000");
//        for (String uid : uidArr) {
//            long hash = HashUtils.hash(uid);
//            long mod2 = Math.abs(hash % 128);
//            if (mod2 >= 0 && mod2 < 16) {
//                String index2 = decimalFormat.format(mod2);
//                String accountConditon = " where login_account = '" + uid +"' ;";
//                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
//                System.out.println(accountSql);
//            }
//        }
//        System.out.println("");
//
//        for (String uid : uidArr) {
//            long hash = HashUtils.hash(uid);
//            long mod2 = Math.abs(hash % 128);
//            if (mod2 >= 16 && mod2 < 32) {
//                String index2 = decimalFormat.format(mod2);
//                String accountConditon = " where login_account = '" + uid +"' ;";
//                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
//                System.out.println(accountSql);
//            }
//        }
//        System.out.println("");
//
//        for (String uid : uidArr) {
//            long hash = HashUtils.hash(uid);
//            long mod2 = Math.abs(hash % 128);
//            if (mod2 >= 32 && mod2 < 48) {
//                String index2 = decimalFormat.format(mod2);
//                String accountConditon = " where login_account = '" + uid +"' ;";
//                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
//                System.out.println(accountSql);
//            }
//        }
//        System.out.println("");
//
//        for (String uid : uidArr) {
//            long hash = HashUtils.hash(uid);
//            long mod2 = Math.abs(hash % 128);
//            if (mod2 >= 48 && mod2 < 64) {
//                String index2 = decimalFormat.format(mod2);
//                String accountConditon = " where login_account = '" + uid +"' ;";
//                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
//                System.out.println(accountSql);
//            }
//        }
//        System.out.println("");
//
//        for (String uid : uidArr) {
//            long hash = HashUtils.hash(uid);
//            long mod2 = Math.abs(hash % 128);
//            if (mod2 >= 64 && mod2 < 80) {
//                String index2 = decimalFormat.format(mod2);
//                String accountConditon = " where login_account = '" + uid +"' ;";
//                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
//                System.out.println(accountSql);
//            }
//        }
//        System.out.println("");
//
//        for (String uid : uidArr) {
//            long hash = HashUtils.hash(uid);
//            long mod2 = Math.abs(hash % 128);
//            if (mod2 >= 80 && mod2 < 96) {
//                String index2 = decimalFormat.format(mod2);
//                String accountConditon = " where login_account = '" + uid +"' ;";
//                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
//                System.out.println(accountSql);
//            }
//        }
//        System.out.println("");
//
//        for (String uid : uidArr) {
//            long hash = HashUtils.hash(uid);
//            long mod2 = Math.abs(hash % 128);
//            if (mod2 >= 96 && mod2 < 112) {
//                String index2 = decimalFormat.format(mod2);
//                String accountConditon = " where login_account = '" + uid +"' ;";
//                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
//                System.out.println(accountSql);
//            }
//        }
//        System.out.println("");
//
//        for (String uid : uidArr) {
//            long hash = HashUtils.hash(uid);
//            long mod2 = Math.abs(hash % 128);
//            if (mod2 >= 112 && mod2 < 128) {
//                String index2 = decimalFormat.format(mod2);
//                String accountConditon = " where login_account = '" + uid +"' ;";
//                String accountSql = "select login_account,memberid from yx_member_account_" + index2 + accountConditon;
//                System.out.println(accountSql);
//            }
//        }
//        System.out.println("");

    }

//    @GetMapping("/generate/user_incr_daily")
//    public String userIncrByDay(
//            @RequestParam(name = "day", required = false, defaultValue = "") Long day
//    ) {
//        try {
//            if (StringUtils.isEmpty(day)) {
//                return "param empty";
//            }
//
//            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
//            String time="2019-08-01";
//            Date date = format.parse(time);
//
//            return String.valueOf(date.getTime());
//
//        } catch (Exception e) {
//            return "error";
//        }
//    }
}
