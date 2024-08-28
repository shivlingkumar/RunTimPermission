package com.example.runtimepermission

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.runtimepermission.adater.ImageAdapter
import com.example.runtimepermission.db.ImageItem
import java.util.UUID

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
  //  private lateinit var adapters: ImageAdapter

    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8,0,8,0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

         viewPager2 = findViewById(R.id.roomImageSlider)


        //        val imageList = arrayListOf(
//            ImageItem(
//                UUID.randomUUID().toString(),
//                "android.resource://" + packageName + "/" + R.drawable.image1
//            ),
//            ImageItem(
//                UUID.randomUUID().toString(),
//                "android.resource://" + packageName + "/" + R.drawable.image2
//            ),
//            ImageItem(
//                UUID.randomUUID().toString(),
//                "android.resource://" + packageName + "/" + R.drawable.image3
//            ),
//            ImageItem(
//                UUID.randomUUID().toString(),
//                "android.resource://" + packageName + "/" + R.drawable.image4
//            ),
//            ImageItem(
//                UUID.randomUUID().toString(),
//                "android.resource://" + packageName + "/" + R.drawable.image5
//            ),
//            ImageItem(
//                UUID.randomUUID().toString(),
//                "android.resource://" + packageName + "/" + R.drawable.image6
//            )
//        )

        val imageList = arrayListOf(
            ImageItem(
                UUID.randomUUID().toString(),
                "https://static.cricbuzz.com/a/img/v1/1080x608/i1/c509868/virat-kohli-and-rohit-sharma-signed-off-in-style.jpg"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://english.varthabharati.in/storage/uploads/sports/bumra_vb_12.gif"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://image.cnbcfm.com/api/v1/image/105968995-1560512715227rtx6z8xd.jpg?v=1583511384"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://d3sc42dkmius1e.cloudfront.net/Upload/669/CMS/News/Photos/1492a5fb-ae6.jpg"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://imgv3.fotor.com/images/slider-image/A-clear-close-up-photo-of-a-woman.jpg"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://fastly.picsum.photos/id/778/500/500.jpg?hmac=jZLZ6WV_OGRxAIIYPk7vGRabcAGAILzxVxhqSH9uLas"
            )
        )


        val imageAdapter = ImageAdapter()
        viewPager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        val slideDotLL = findViewById<LinearLayout>(R.id.slideDotLL)
        val dotsImage = Array(imageList.size) { ImageView(this) }

        dotsImage.forEach {
            it.setImageResource(
                R.drawable.non_active_dot
            )
            slideDotLL.addView(it,params)
        }

        // default first dot selected
        dotsImage[0].setImageResource(R.drawable.active_dot)

        pageChangeListener = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                dotsImage.mapIndexed { index, imageView ->
                    if (position == index){
                        imageView.setImageResource(
                            R.drawable.active_dot
                        )
                    }else{
                        imageView.setImageResource(R.drawable.non_active_dot)
                    }
                }
                super.onPageSelected(position)
            }
        }
        viewPager2.registerOnPageChangeCallback(pageChangeListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2.unregisterOnPageChangeCallback(pageChangeListener)
    }
}