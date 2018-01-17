
TPBScraper
==========

A Server side app that aims to take requests and scrape pirate bay and then return them as a nice json response

It takes a http request maps it using enums to a pirate bay request and scrapes the html to a json respons

-	`http://127.0.0.1:8080/browse?sortedBy=SEEDS&pageNumber=0&category=Movies`

is equivilent to 

-	`https://pirateproxy.cam/browse/201/0/7`

Routes
======

-	`GET /search?searchTerm=<the string to queary>&sortedBy=<enum of SortedBy>&pageNumber=<page number (only 30 results are showed per page)>&category=<enum of Category>`
-	`GET /browse?sortedBy=<enum of SortedBy>&pageNumber=<page number (only 30 results are showed per page)>&category=<enum of Category>`
-	`GET /changeMirrorUrl?newMirrorUrl=<url new mirror to scrape from>`

Example Response
==========
(this is a list of one, there could be up to 30 results in the list)

```json
 [
   {
      "Name":"Hello Dolly (Comedy 1969) Barbra Streisand 720p BrRip",
      "Magnet":"magnet:?xt\u003durn:btih:a57c433dc552afa5f12856711d5168100d2e4470\u0026dn\u003dHello+Dolly+%28Comedy+1969%29+Barbra+Streisand++720p++BrRip\u0026tr\u003dudp%3A%2F%2Ftracker.leechers-paradise.org%3A6969\u0026tr\u003dudp%3A%2F%2Fzer0day.ch%3A1337\u0026tr\u003dudp%3A%2F%2Fopen.demonii.com%3A1337\u0026tr\u003dudp%3A%2F%2Ftracker.coppersurfer.tk%3A6969\u0026tr\u003dudp%3A%2F%2Fexodus.desync.com%3A6969",
      "Link":"/torrent/11334283/Hello_Dolly_(Comedy_1969)_Barbra_Streisand__720p__BrRip",
      "Uploaded":"10-31 2014",
      "Size":"774.76 MiB",
      "Uled":"BurtonW",
      "Seeds":12,
      "Leechers":3,
      "CategoryParent":"Video",
      "Category":"Movies"
   }
]
```

Possible Category
==========
    All
    AllAudio
    AllVideo
    AllApplication
    AllGames
    AllPorn
    AllOther
    Music
    Audiobooks
    Soundclips
    FLAC
    OtherAudio
    Movies
    MoviesDVDR
    Musicvideos
    Movieclips
    TVshows
    HandheldVideo
    HDMovies
    HDTVshows
    Movies3D
    OtherVideo
    WindowsApplications
    MacApplications
    UNIXApplications
    HandheldApplications
    IOSApplications
    AndroidApplications
    OtherOSApplications
    PCGames
    MacGames
    PSxGames
    XBOX360Games
    WiiGames
    HandheldGames
    IOSGames
    AndroidGames
    OtherGames
    MoviesPorn
    MoviesDVDRPorn
    PicturesPorn
    GamesPorn
    HDMoviesPorn
    MovieclipsPorn
    OtherPorn
    Ebooks
    Comics
    Pictures
    Covers
    Physibles
    Other
    
Possible SortType
==========
    NAME
    NAME_DESCENDING
    UPLOAD
    UPLOAD_DESCENDING
    SIZE
    SIZE_DESCENDING
    SEEDS
    SEEDS_DESCENDING
    LEECHERS
    LEECHERS_DESCENDING
    ULED_BY
    ULED_BY_DESCENDING
    TYPE
    TYPE_DESCENDING
    DEFAULT
    
Changing mirror url
==========
This is useful if a mirror is not responding or has been taken down, please see the route mentioned above, urls should be formeted like the following - http://google.com
note the http to start and no trailing /


Deployment - Only tested on Ubuntu 16.04 - (work in progress - missing details)
==========

```
setup java -
sudo add-apt-repository ppa:webupd8team/java
sudo apt update; sudo apt install oracle-java8-installer
sudo apt install oracle-java8-set-default

setup nginx - (needs more detail)


cd $HOME
git clone https://github.com/arranlomas/TPBScraper.git

cd TPBScraper
./gradlew run
```
