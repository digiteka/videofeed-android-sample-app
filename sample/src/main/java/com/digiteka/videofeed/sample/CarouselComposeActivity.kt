package com.digiteka.videofeed.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.digiteka.videofeed.ui.carousel.VideoFeedCarousel

class CarouselComposeActivity : ComponentActivity() {

	companion object {
		private const val EXTRA_MDTK: String = "CarouselComposeActivity.EXTRA_MDTK"
		private const val EXTRA_ADUNITPATH: String = "CarouselComposeActivity.EXTRA_ADUNITPATH"

		fun newInstance(activity: AppCompatActivity, mdtk: String, adUnitPath: String?): Intent {
			return Intent(activity, CarouselComposeActivity::class.java)
				.putExtra(EXTRA_MDTK, mdtk)
				.putExtra(EXTRA_ADUNITPATH, adUnitPath)
		}
	}

	private val mdtk by lazy { checkNotNull(intent.getStringExtra(EXTRA_MDTK)) { "Missing mdtk" } }
	private val adUnitPath by lazy { intent.getStringExtra(EXTRA_ADUNITPATH) }

	private val onVideoClicked = { mdtk: String, videoId: String ->
		runOnUiThread {
			Toast.makeText(this, "Video clicked: $videoId", Toast.LENGTH_LONG).show()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Content(mdtk, adUnitPath, onVideoClicked)
		}
	}
}

@Composable
fun Content(mdtk: String, adUnitPath: String?, onVideoClicked: (mdtk: String, videoId: String) -> Unit = { _, _ -> }) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.windowInsetsPadding(WindowInsets.navigationBars),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.SpaceEvenly
	) {
		VideoFeedCarousel(
			mdtk = mdtk,
			adUnitPath = adUnitPath,
			modifier = Modifier
				.fillMaxWidth()
				.height(160.dp)
		)
		VideoFeedCarousel(
			mdtk = mdtk,
			adUnitPath = adUnitPath,
			modifier = Modifier
				.fillMaxWidth()
				.height(160.dp),
			onVideoClicked = onVideoClicked
		)
	}
}