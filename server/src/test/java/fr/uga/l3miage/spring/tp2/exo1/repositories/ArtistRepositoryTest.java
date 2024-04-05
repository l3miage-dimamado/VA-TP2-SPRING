package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.exo1.enums.GenreMusical;
import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void TestRequestCountByGenreMusicalEquals(){
        //given
        //On crée 2 artists
        ArtistEntity artist1 = ArtistEntity
                .builder()
                .name("artist1")
                .biography("né en 1980")
                .genreMusical(GenreMusical.RAP)
                .build();
        ArtistEntity artist2 = ArtistEntity
                .builder()
                .name("artist2")
                .biography("né en 2000")
                .genreMusical(GenreMusical.SLAM)
                .build();
        ArtistEntity artist3 = ArtistEntity
                .builder()
                .name("artist3")
                .biography("né en 2008")
                .genreMusical(GenreMusical.RAP)
                .build();

        //On les enregistre en BD
        artistRepository.save(artist1);
        artistRepository.save(artist2);
        artistRepository.save(artist3);

        //when
        // on appelle la methode de comptage des artiste
        int slamCountResponses = artistRepository.countByGenreMusicalEquals(GenreMusical.SLAM);
        int rapCountResponses = artistRepository.countByGenreMusicalEquals(GenreMusical.RAP);
        int classicCountResponses = artistRepository.countByGenreMusicalEquals(GenreMusical.CLASSIC);
        //then
        //On vérifie que le comptage est correct
        assertThat(slamCountResponses).isEqualTo(1);
        assertThat(rapCountResponses).isEqualTo(2);
        assertThat(classicCountResponses).isEqualTo(0);
    }


}