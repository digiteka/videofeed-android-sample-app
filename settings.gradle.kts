import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties
import kotlin.apply

pluginManagement {
	repositories {
		google {
			content {
				includeGroupByRegex("com\\.android.*")
				includeGroupByRegex("com\\.google.*")
				includeGroupByRegex("androidx.*")
			}
		}
		mavenCentral()
		gradlePluginPortal()
	}
}

val localProperties = Properties().apply {
	val localProperties = file("local.properties")
	if (localProperties.exists() && localProperties.isFile) {
		InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
			load(reader)
		}
	}
}

dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
		maven{
			url = uri("https://jitpack.io")
			credentials {
				username = localProperties.getProperty("DIGITEKA_JITPACK_ACCESS_KEY")
			}
		}
	}
}

rootProject.name = "VideoFeed-Sample"
include(":sample")
