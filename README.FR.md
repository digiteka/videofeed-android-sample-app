# Librairie VideoFeed de Digiteka

[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md)
[![fr](https://img.shields.io/badge/lang-fr-blue.svg)](README.FR.md)

La librairie VideoFeed de Digiteka fournit des composants d'affichage de vidéos :
- un carousel d'aperçus
- un lecteur vertical de vidéos, avec navigation par scroll et swipe

# Installation

Ajouter la dépendance à votre fichier `build.gradle`:

``` kotlin    
dependencies {    
  implementation("com.digiteka.android:videofeed-android:2.0.0")  
}   
```

Et ajouter le token d'accès jitpack à votre projet dans votre fichier `settings.gradle.kt` :

``` kotlin
dependencyResolutionManagement {
    maven{
        url = uri("https://jitpack.io")
        credentials {
            username = "your_access_key_here"
        }
    }
}
```

# Utilisation

Vous devez disposer d'une clé MDTK pour utiliser la librairie.
En option, un chemin d'AdUnit peut aussi être renseigné au format `/{networkCode}/{adBlockPath}`.

## Carousel

Le carousel d'aperçus est un composant d'affichage de vignettes de vidéos en mode horizontal. 
Il peut être intégré via un composant XML ou Compose.

### XML

``` xml
<com.digiteka.videofeed.ui.carousel.VideoFeedCarousel
    android:id="@+id/videoFeedCarousel"
    android:layout_width="match_parent"
    android:layout_height="@dimen/carousel_height" />
```

Le composant doit ensuite être chargé via la méthode `load()` prenant en paramètre votre clé MDTK et le chemin d'AdUnit (optionnel) :

``` kotlin
binding.videoFeedCarousel.load(mdtk, adUnitPath)
```

Par défaut, le clic sur une vidéo ouvre le lecteur vidéo (VideoFeed) en plein écran dans une nouvelle activity.
Il est possible de surcharger ce comportement en ajoutant un listener :

``` kotlin
binding.videoFeedCarousel.onVideoClicked = { mdtk, videoId ->
  // Handle video click
}
```

### Compose

``` kotlin
VideoFeedCarousel(
    modifier = Modifier
        .fillMaxWidth()
        .height(AppTheme.dimens.carouselHeight),
    mdtk = mdtk,
    adUnitPath = adUnitPath,
    onVideoClicked = { mdtk, videoId ->
      // Handle video click
    }
)
```

Le paramètre `onVideoClicked` est optionnel. S'il n'est pas renseigné, le clic sur une vidéo ouvrira le lecteur vidéo (VideoFeed) en plein écran dans une nouvelle activity.

## VideoFeed

Le VideoFeed est un lecteur vidéo vertical, avec navigation par scroll et swipe.
Il peut être intégré via une activity ou un fragment.

### Activity

``` kotlin
startActivity(VideoFeedActivity.newInstance(context, mdtk, videoId, adUnitPath))
```

La paramètre `videoId` est nullable. Si renseigné, le lecteur vidéo s'ouvrira directement sur la vidéo correspondante ; sinon, le lecteur s'ouvrira sur la première vidéo du feed.

### Fragment

`VideoFeedFragment` est un fragment AndroidX et peut être intégré comme tout autre fragment.
Dans l'exemple suivant, il est inséré avec un `FragmentContainerView` dans un layout XML.

``` xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/defaultUiActivityContainerView"
    android:name="com.digiteka.videofeed.ui.VideoFeedFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />   
```

Il doit ensuite être initialisé avec la clé MDTK :

``` kotlin
videoFeedFragment.load(mdtk, videoId, adUnitPath)
```

La paramètre `videoId` est nullable. Si renseigné, le lecteur vidéo s'ouvrira directement sur la vidéo correspondante ; sinon, le lecteur s'ouvrira sur la première vidéo du feed.