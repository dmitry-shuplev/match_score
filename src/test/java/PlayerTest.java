import models.Player;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import service.PlayerDao;
import utils.HibernateUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    static PlayerDao pd;
    String expectedPlayerName;
    String getingPlayerName;

    @BeforeAll
    static void createPlayerDao() {
        SessionFactory sf = HibernateUtils.getSessionFactory();
        pd = new PlayerDao(sf);
    }

    @BeforeEach
    void createInputParametr() {
        expectedPlayerName = "Гурген Гургенович";
        getingPlayerName = "гурген 123 гургенович     ";
    }

    @Test
    public void FormatPlayerNameRight() {
        String formatedPlayerName = pd.nameFormated(getingPlayerName);
        assertEquals(expectedPlayerName, formatedPlayerName);
    }

    @Test
    public void writenToDb() {
        Player player = new Player();
        pd.crateNewPlayer(expectedPlayerName);
        player = pd.getByName(expectedPlayerName);
        assertTrue(player.getName().equals(expectedPlayerName));
        pd.deleteByName(expectedPlayerName);
    }

    @Test
    public void priventDuplicatePlayer(){
        pd.crateNewPlayer(expectedPlayerName);
        pd.crateNewPlayer(expectedPlayerName);

    }

    @AfterEach
    void destroyInputParament() {
        expectedPlayerName = null;
        getingPlayerName = null;
    }

    @AfterAll
    static void destryouCoonectionDB() {
        HibernateUtils.shutDown();
    }
}
