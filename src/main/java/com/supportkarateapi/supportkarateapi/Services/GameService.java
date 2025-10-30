package com.supportkarateapi.supportkarateapi.Services;

import com.supportkarateapi.supportkarateapi.Models.Game;
import com.supportkarateapi.supportkarateapi.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game game) {
        // For creation, ensure the entity is new (id is null)
        // Create a new instance to avoid detached entity issues
        Game newGame = new Game();
        newGame.setName(game.getName());
        newGame.setGenre(game.getGenre());
        newGame.setPrice(game.getPrice());
        return gameRepository.save(newGame);
    }

    public Game updateGame(Long id, Game game) {
        if (gameRepository.existsById(id)) {
            game.setId(id);
            return gameRepository.save(game);
        } else {
            throw new RuntimeException("Game not found with id: " + id);
        }
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public List<Game> resetAndInitData() {
        gameRepository.deleteAll();
        List<Game> initialGames = List.of(
            new Game("The Witcher 3", "RPG", 39.99),
            new Game("FIFA 2023", "Sports", 59.99),
            new Game("Minecraft", "Sandbox", 29.99),
            new Game("Call of Duty", "Shooter", 49.99),
            new Game("The Legend of Zelda", "Adventure", 59.99)
        );
        return gameRepository.saveAll(initialGames);
    }
}
