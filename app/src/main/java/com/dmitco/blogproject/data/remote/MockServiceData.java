package com.dmitco.blogproject.data.remote;


import android.util.Log;

import com.dmitco.blogproject.application.Constants;
import com.dmitco.blogproject.application.G;
import com.dmitco.blogproject.model.Login;
import com.dmitco.blogproject.model.Post;
import com.dmitco.blogproject.model.User;
import com.dmitco.blogproject.model.Version;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockServiceData {

    public static final class MockService implements ServiceApi  {

        private final BehaviorDelegate<ServiceApi> delegate;


        public MockService(BehaviorDelegate<ServiceApi> delegate) {
            this.delegate = delegate;

        }

        @Override
        public Call<Login> login(String userName, String pass) {
            Version version = new Version();
            version.setActive(true);
            version.setImportant(false);
            version.setUrlVersion("http:test.com");
            version.setVersionId(1);
            version.setVersionCode(1);
            version.setVersionName("1.0");

            String token = "jsdsdjsjfjjkjsdkjksdksjdkskdjskd";

            User user = new User();
            user.setUserId(1);
            user.setActiveCode("aaaa");
            user.setUserName("davidBU.");
            user.setName("داود");
            user.setFamily("یونسی");
            user.setEmail("david@gmail.com");
            user.setRegisterDate("1398/10/14 20:13:08");

            Login login = new Login();
            login.setToken(token);
            login.setUser(user);
            login.setVersion(version);
            return delegate.returningResponse(login).login(userName, pass);
        }

        @Override
        public Call<List<Post>> getPosts(int pageNumber, int pageSize) {

            G.log(Constants.TAG, "getPosts() called with: pageNumber = [" + pageNumber + "], pageSize = [" + pageSize + "]");
            List<Post> list = new ArrayList<>();

            if (!(pageNumber >3)) {
                for (int i = 0; i < pageSize; i++) {
                    Post post = new Post();
                    post.setPostId(1);
                    post.setPostTitle("بهترین ساعت هوشمند و دستبند هوشمند با قیمت های مختلف");
                    post.setGroupTitle("تکنولوژی");
                    post.setPostText("ساعت\u200Cها و دستبندهای هوشمند در بازار، تنوع زیادی پیدا کرده\u200Cاند اما محصولات شاخص کدام\u200Cاند و کدام ارزش خرید بیشتری دارند؟\n" +
                            "\n" +
                            "ساعت هوشمند، گجتی نو رسیده که با وجود دست نیافتن به اهداف و دورنمای ترسیم شده برای آن، اما به محصولی محبوب بدل شده\u200C است؛ آنچه مسلم است این ابزار هنوز آنطور که باید جای خود را در بین گجت\u200Cهای ضروری پیدا نکرده\u200Cاند؛ نزدیک به نیم قرن از ظهور ساعت\u200Cهای مجهز به ماشین\u200Cحساب می\u200Cگذرد و همواره جای خالی یک گجت همه\u200Cکاره\u200Cی با روی مچ بسته شود حس می\u200Cشد.\n" +
                            "\n" +
                            "بسیاری از ساعت\u200Cهای هوشمند و دستبند\u200Cهای سلامتی امروزی با نمایش اعلان\u200Cها، پیام\u200Cها، پایش ضربان قلب، پایش وضعیت خواب و ایفای نقش یک همراه در فعالیت\u200Cهای ورزشی با نزدیک\u200Cتر کردن ظاهر خود به ساعت\u200Cهای کلاسیک به\u200Cدنبال همه\u200Cگیر شدن هستند. ساعت\u200Cهای هوشمند با چالش\u200Cهای جدی همچون نیازمندی به شارژ رو\u200Cبه\u200Cرو هستند که با وجود بهبودهای اندک هنوز مانع اصلی رشد آن\u200Cها محسوب می\u200Cشود. \n" +
                            "\n" +
                            "پیش از خرید ساعت یا دستبند هوشمند، این سؤال که «آیا به چنین وسیله\u200Cای نیاز دارم» را از خود بپرسید. ساعت هوشمند و دستبندهای سلامتی برای ورزشکاران حرفه\u200Cای و حتی غیر حرفه\u200Cای امکانات متنوعی از جمله رصد ضربان قلب، شدت تمرینات، سرعت و میزان آن را را ارائه می\u200Cکند. با اتصال هدست\u200Cهای بی\u200Cسیم می\u200Cتوان بدون همراه داشتن گوشی موبایل با خیالی راحت در حال ورزش موسیقی گوشی کرد. امکان پاسخ به تماس در برخی از ساعت\u200Cهای هوشمند وجود دارد و می\u200Cتوان کنترل\u200Cهای ساده\u200Cی گوشی و ارتباطات از جمله تماس، پاسخ سریع به پیام و مواردی از این دست را انجام داد.");
                    post.setImageName("https://cdn01.zoomit.ir/2019/12/95ce56ac-1fca-4c3b-9215-216b9e48a49b.jpg");
                    post.setCreateDate("یکشنبه, ۲۱ اردیبهشت ۹۹ ساعت");
                    post.setUserCreateId(1);
                    post.setUserFirstName("حمید");
                    post.setUserLastName("ملکی"+i);
                    post.setUserAvatar("https://cdn01.zoomit.ir/Avatars/ba36cdcd-b070-444d-b413-6e131040c5e8.jpg");

                    list.add(post);
                }
            }
            return delegate.returningResponse(list).getPosts(pageNumber, pageSize);
        }


    }


}