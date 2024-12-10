package mk.ukim.finki.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab.model.Album;
import mk.ukim.finki.lab.model.Artist;
import mk.ukim.finki.lab.model.Song;
import mk.ukim.finki.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.lab.repository.jpa.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artistList = null;
    public static List<Song> songsList = null;
    public static List<Album> albumList = null;

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    public DataHolder(AlbumRepository albumRepository, SongRepository songRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @PostConstruct
    public void init() {
        artistList = new ArrayList<>();
        if (artistRepository.count() == 0) {
            artistList.add(new Artist("Taylor", "Swift", "One of the best-selling artists in the world."));
            artistList.add(new Artist("Ed", "Sheeran", "Known for his incredible songwriting."));
            artistList.add(new Artist("The Weeknd", "", "Canadian singer and record producer."));
            artistList.add(new Artist("Lady", "Gaga", "A versatile artist known for her dynamic vocals."));
            artistList.add(new Artist("Post", "Malone", "American rapper and singer."));
            this.artistRepository.saveAll(artistList);
        }

        albumList = new ArrayList<>();
        if (this.albumRepository.count() == 0) {
            albumList.add(new Album("1989", "Pop", "2014"));
            albumList.add(new Album("Divide", "Pop", "2017"));
            albumList.add(new Album("After Hours", "R&B", "2020"));
            albumList.add(new Album("Chromatica", "Dance-Pop", "2020"));
            albumList.add(new Album("Hollywood's Bleeding", "Hip-Hop", "2019"));
            this.albumRepository.saveAll(albumList);
        }

        songsList = new ArrayList<>();
        if (this.songRepository.count() == 0) {
            songsList.add(new Song("Shake It Off", "Pop", 2014, new ArrayList<>(), albumList.get(0)));
            songsList.add(new Song("Perfect", "Pop", 2017, new ArrayList<>(), albumList.get(1)));
            songsList.add(new Song("Blinding Lights", "R&B", 2020, new ArrayList<>(), albumList.get(2)));
            songsList.add(new Song("Rain On Me", "Dance-Pop", 2020, new ArrayList<>(), albumList.get(3)));
            songsList.add(new Song("Circles", "Hip-Hop", 2019, new ArrayList<>(), albumList.get(4)));
            this.songRepository.saveAll(songsList);
        }
    }
}
