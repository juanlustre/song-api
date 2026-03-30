package com.lustre.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lustre/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songRepository.save(song);
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Optional<Song> song = songRepository.findById(id);
        return song.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song updatedSong) {
        Optional<Song> existingSong = songRepository.findById(id);

        if (existingSong.isPresent()) {
            Song song = existingSong.get();

            song.setTitle(updatedSong.getTitle());
            song.setArtist(updatedSong.getArtist());
            song.setAlbum(updatedSong.getAlbum());
            song.setGenre(updatedSong.getGenre());
            song.setUrl(updatedSong.getUrl());

            return ResponseEntity.ok(songRepository.save(song));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search/title")
    public List<Song> searchByTitle(@RequestParam String keyword) {
        return songRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @GetMapping("/search/artist")
    public List<Song> searchByArtist(@RequestParam String keyword) {
        return songRepository.findByArtistContainingIgnoreCase(keyword);
    }

    @GetMapping("/search/album")
    public List<Song> searchByAlbum(@RequestParam String keyword) {
        return songRepository.findByAlbumContainingIgnoreCase(keyword);
    }

    @GetMapping("/search/genre")
    public List<Song> searchByGenre(@RequestParam String keyword) {
        return songRepository.findByGenreContainingIgnoreCase(keyword);
    }

    @GetMapping("/search/{keyword}")
    public List<Song> searchAll(@PathVariable String keyword) {
        return songRepository
                .findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCaseOrAlbumContainingIgnoreCaseOrGenreContainingIgnoreCase(
                        keyword, keyword, keyword, keyword
                );
    }
}