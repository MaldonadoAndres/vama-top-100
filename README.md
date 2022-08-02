# Vama Top 100

I tink the code is quite self explanatory due to the simple nature of the test but there are 3 things I would like to clarify:

---
 First why the models extends from Parcelabel insted of Serializable.

 This is basically because to use realm all the objects need to extend from RealmObject and this one aren't really well managed when you use them in intents like the one used for opening the url of the album in the Detail Fragment

---

---
 Second this fragment of code
 

 ```
  for (album in albums) {
        album.realmGenres = album.genres.toRealmList()
    }
 ```
In the `AlbumRepositoryImplementation.kt` thats because realm only accepts for relationships RealmList but the parsing library(Gson) when creating the Album object give it an ArrayList so I had to create two variables in the Album class one for Gson and the other for Real to actually save the information

---

---
Lastly you may see the cover of the album `Un verano sin ti` when some album cover are loading this is because I really like this album
so I decided to use it as placeholder while the album cover is loading

---
