package models

enum class Category {
    All,
    AllAudio,
    AllVideo,
    AllApplication,
    AllGames,
    AllPorn,
    AllOther,
    Music,
    Audiobooks,
    Soundclips,
    FLAC,
    OtherAudio,
    Movies,
    MoviesDVDR,
    Musicvideos,
    Movieclips,
    TVshows,
    HandheldVideo,
    HDMovies,
    HDTVshows,
    Movies3D,
    OtherVideo,
    WindowsApplications,
    MacApplications,
    UNIXApplications,
    HandheldApplications,
    IOSApplications,
    AndroidApplications,
    OtherOSApplications,
    PCGames,
    MacGames,
    PSxGames,
    XBOX360Games,
    WiiGames,
    HandheldGames,
    IOSGames,
    AndroidGames,
    OtherGames,
    MoviesPorn,
    MoviesDVDRPorn,
    PicturesPorn,
    GamesPorn,
    HDMoviesPorn,
    MovieclipsPorn,
    OtherPorn,
    Ebooks,
    Comics,
    Pictures,
    Covers,
    Physibles,
    Other;

    fun getTpbValue(): Int {
        return when (this) {
            Category.All -> 0
            Category.AllAudio -> 100
            Category.AllVideo -> 200
            Category.AllApplication -> 300
            Category.AllGames -> 400
            Category.AllPorn -> 500
            Category.AllOther -> 60
            Category.Music -> 101
            Category.Audiobooks -> 102
            Category.Soundclips -> 103
            Category.FLAC -> 104
            Category.OtherAudio -> 19
            Category.Movies -> 201
            Category.MoviesDVDR -> 202
            Category.Musicvideos -> 203
            Category.Movieclips -> 204
            Category.TVshows -> 205
            Category.HandheldVideo -> 206
            Category.HDMovies -> 207
            Category.HDTVshows -> 208
            Category.Movies3D -> 209
            Category.OtherVideo -> 29
            Category.WindowsApplications -> 301
            Category.MacApplications -> 302
            Category.UNIXApplications -> 303
            Category.HandheldApplications -> 304
            Category.IOSApplications -> 305
            Category.AndroidApplications -> 306
            Category.OtherOSApplications -> 39
            Category.PCGames -> 401
            Category.MacGames -> 402
            Category.PSxGames -> 403
            Category.XBOX360Games -> 404
            Category.WiiGames -> 405
            Category.HandheldGames -> 406
            Category.IOSGames -> 407
            Category.AndroidGames -> 408
            Category.OtherGames -> 49
            Category.MoviesPorn -> 501
            Category.MoviesDVDRPorn -> 502
            Category.PicturesPorn -> 503
            Category.GamesPorn -> 504
            Category.HDMoviesPorn -> 505
            Category.MovieclipsPorn -> 506
            Category.OtherPorn -> 59
            Category.Ebooks -> 601
            Category.Comics -> 602
            Category.Pictures -> 603
            Category.Covers -> 604
            Category.Physibles -> 605
            Category.Other -> 699
        }
    }
}
