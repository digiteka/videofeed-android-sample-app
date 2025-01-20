package com.digiteka.videofeed.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.digiteka.videofeed.sample.databinding.CarouselActivityBinding

class CarouselActivity : AppCompatActivity() {

	companion object {
		private const val EXTRA_MDTK: String = "CarouselActivity.EXTRA_MDTK"
		private const val EXTRA_ADUNITPATH: String = "CarouselActivity.EXTRA_ADUNITPATH"

		fun newInstance(activity: AppCompatActivity, mdtk: String, adUnitPath: String?): Intent {
			return Intent(activity, CarouselActivity::class.java)
				.putExtra(EXTRA_MDTK, mdtk)
				.putExtra(EXTRA_ADUNITPATH, adUnitPath)
		}
	}

	private val mdtk by lazy { checkNotNull(intent.getStringExtra(EXTRA_MDTK)) { "Missing mdtk" } }
	private val adUnitPath by lazy { intent.getStringExtra(EXTRA_ADUNITPATH) }

	private val binding: CarouselActivityBinding by lazy {
		CarouselActivityBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		binding.videoFeedCarousel1.load(mdtk, adUnitPath)

		binding.videoFeedCarousel2.load(mdtk, adUnitPath)
		binding.videoFeedCarousel2.onVideoClicked = { mdtk, videoId ->
			runOnUiThread {
				Toast.makeText(this, "Video clicked: $videoId", Toast.LENGTH_LONG).show()
			}
		}
	}


}