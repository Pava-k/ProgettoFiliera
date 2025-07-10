package unicam.progettofiliera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.progettofiliera.infrastructure.AnimatoreRepository;
import unicam.progettofiliera.models.Animatore;

@RestController
@RequestMapping("/animatori")
public class AnimatoreController {

    private final AnimatoreRepository animatoreRepository;

    @Autowired
    public AnimatoreController(AnimatoreRepository animatoreRepository) {
        this.animatoreRepository = animatoreRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Animatore> add(@RequestBody Animatore animatore) {
        animatoreRepository.save(animatore);
        return ResponseEntity.ok(animatore);
    }

}
