package tech.thdev.kotlin_udemy_sample.view.detail_photo_id

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_parcelable.*
import tech.thdev.base.view.BasePresenterActivity
import tech.thdev.kotlin_udemy_sample.R
import tech.thdev.kotlin_udemy_sample.constant.Constant
import tech.thdev.kotlin_udemy_sample.data.FlickrPhoto
import tech.thdev.kotlin_udemy_sample.data.model.PhotoDataSource
import tech.thdev.kotlin_udemy_sample.view.detail_photo_id.presenter.DetailPhotoIdContract
import tech.thdev.kotlin_udemy_sample.view.detail_photo_id.presenter.DetailPhotoIdPresenter

class DetailPhotoIdActivity : BasePresenterActivity<DetailPhotoIdContract.View, DetailPhotoIdContract.Presenter>(), DetailPhotoIdContract.View {

    private val tvTitle by lazy {
        findViewById(R.id.tv_title) as TextView
    }

    private val imgView by lazy {
        findViewById(R.id.img_view) as ImageView
    }

    override fun onCreatePresenter() = DetailPhotoIdPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_parcelable)

        presenter?.photoDataSource = PhotoDataSource

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // TODO getString extra 정의
        val photoId = intent.getStringExtra(Constant.KEY_PHOTO_DATA)
        presenter?.loadPhotoInfo(photoId)
    }

    override fun updateItem(photo: FlickrPhoto) {
        Glide.with(this)
                .load(photo.getImageUrl())
                .fitCenter()
                .crossFade()
                .into(imgView)

        tvTitle.text = photo.title.toString()
    }
}