package com.leohulabb.data;

import com.google.gson.Gson;

import java.util.Random;

/**
 * des:假数据
 * Created by xsf
 * on 2016.07.11:14
 */
public class DatasEntity {

    public static TestData getDataTest()
    {
        String str = "{\"ApplyTime\":null,\"Becareful\":null,\"CreateId\":\"User100124165\",\"EndTime\":null,\"ExpenseType\":1,\"FancierAddress\":\"杭州市上城区仕海足球\",\"FancierBattleId\":\"FBattle100000456\",\"FancierCircleId\":null,\"FancierImage\":\"http:\\/\\/picqn.hulabanban.com\\/e4db30a296b4439ab15795ecc27f2b79.jpg\",\"FancierName\":\"上城区足球挑战赛\",\"FancierState\":1,\"FancierTime\":\"2016\\/10\\/12 9:00:00\",\"GuesTcaptainId\":null,\"GuestId\":null,\"GuestName\":null,\"GuestTeamId\":null,\"GuestTeamName\":null,\"HomeTeamId\":\"Team100000313\",\"HomeTeamName\":null,\"ImageUrl\":null,\"IsTrial\":false,\"Lat\":null,\"Lng\":null,\"Mb\":null,\"ReadNum\":0,\"ReleaseTeamId\":null,\"Result\":null,\"ResultState\":0,\"ResultTime\":null,\"Score\":null,\"ShareNum\":0,\"TotalScore\":0,\"Type\":2,\"UserName\":\"余生等汝归\",\"WinRate\":null}";
        return new Gson().fromJson(str, TestData.class);
    }

    /**
     * 图片
     */
    private static String[]Urls={"http://d.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c6e453882a9f16fdfaaf516729.jpg", "http://h.hiphotos.baidu.com/image/pic/item/30adcbef76094b36db47d2e4a1cc7cd98c109de6.jpg","http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c27dc0dcdd52a6059242da6cc.jpg"
            ,"http://c.hiphotos.baidu.com/image/h%3D200/sign=d21f63f99d3df8dcb93d8891fd1072bf/78310a55b319ebc415951b978026cffc1f1716ca.jpg","http://d.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d22dc28839a442309f79052d265.jpg"
    ,"http://c.hiphotos.baidu.com/image/pic/item/03087bf40ad162d9d0e7560313dfa9ec8a13cda7.jpg","http://g.hiphotos.baidu.com/image/h%3D200/sign=16f4ef3e35adcbef1e3479069cae2e0e/6d81800a19d8bc3e7763d030868ba61ea9d345e5.jpg"
    ,"http://g.hiphotos.baidu.com/image/pic/item/8d5494eef01f3a29a3b0e6c49b25bc315c607cbb.jpg","http://c.hiphotos.baidu.com/image/h%3D200/sign=548da2d73f6d55fbdac671265d224f40/a044ad345982b2b7a2b8f7cd33adcbef76099b90.jpg"
    ,"http://g.hiphotos.baidu.com/image/pic/item/7acb0a46f21fbe09359315d16f600c338644ad22.jpg","http://h.hiphotos.baidu.com/image/h%3D200/sign=9d4948d52c738bd4db21b531918a876c/6a600c338744ebf85db15337dbf9d72a6159a7f1.jpg"
    ,"http://e.hiphotos.baidu.com/image/h%3D200/sign=7683f02abc096b639e1959503c328733/203fb80e7bec54e74a142d1bbb389b504fc26a3e.jpg"};

    /**
     * 获取随机图片串
     * @param num
     * @return
     */
    public static String getRandomPhotoUrlString(int num) {
        StringBuilder sdbResult = new StringBuilder();
        if(num>0) {
            String[] photoUrls = new String[num>9?9:num];
            for (int i = 0; i< num ; i++) {
                if ( sdbResult.length() > 0 )
                {
                    sdbResult.append( ";" ).append( Urls[new Random().nextInt(Urls.length)] );
                }else{
                    sdbResult.append( Urls[new Random().nextInt(Urls.length)] );
                }

            }
        }
        return sdbResult.toString();
    }
    /**
     * 获取随机图片串
     * @return
     */
    public static String getRandomPhotoUrl() {
        return  Urls[new Random().nextInt(Urls.length)];
    }
}
