package com.tools.work.Seiya.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class HashUtils {

    public static Long hash(String key) {

        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }
        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }
        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;
        buf.order(byteOrder);
        return h;
    }

    public static void main(String[] args) throws Exception{
    //    DecimalFormat df = new DecimalFormat("000");
    //    long hash = HashUtils.hash("5574330619");
    //    long mod = Math.abs(hash % 128);
    //        System.out.println(df.format(mod));

    //            int day = 86400;
    //            String sqlBase = "select count(1)*128 from yx_member_base_032 where  memberid > 1300131000 and createtime between %d and %d;\n";
    //            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
    //            String time="2019-07-01";
    //            Date date = format.parse(time);
    //
    //            for (int i=0; i < 50; i++) {
    //                System.out.printf(sqlBase, date.getTime()/1000+i*day, date.getTime()/1000+(i+1)*day);
    //            }

        for (int i=1;i <= 7; i++) {
            double x = i * 10000;
            double str = 3.322 * Math.pow(10, -10) * x * x * x - 2.542 * Math.pow(10, -5) * x * x
                    + 0.7173 * x + 11390;
            System.out.println(str);
        }

    }
}
